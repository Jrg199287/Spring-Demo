> 本文由 [简悦 SimpRead](http://ksria.com/simpread/) 转码， 原文地址 https://blog.csdn.net/u010412719/article/details/52136100 版权声明：本文为博主原创文章，未经博主允许不得转载。 https://blog.csdn.net/u010412719/article/details/52136100

# <a></a>《Java 源码分析》：线程池 ThreadPoolExecutor

ThreadPoolExecutor 是 ExecutorService 的一张实现，但是是间接实现。

ThreadPoolExecutor 是继承 AbstractExecutorService。而 AbstractExecutorService 实现了 ExecutorService 接口。

在介绍细节的之前，先介绍下 ThreadPoolExecutor 的结构

![](http://7xknzt.com1.z0.glb.clouddn.com/ThreadPoolExecutor.PNG)

1、线程池需要支持多个线程并发执行，因此有一个线程集合 Collection 来执行线程任务；

2、很显然在多个线程之间协调多个任务，那么就需要一个线程安全的任务集合，同时还需要支持阻塞、超时操作，那么 BlockingQueue 是必不可少的；

3、既然是线程池，出发点就是提高系统性能同时降低资源消耗，那么线程池的大小就有限制，因此需要有一个核心线程池大小（线程个数）和一个最大线程池大小（线程个数），有一个计数用来描述当前线程池大小；

4、如果是有限的线程池大小，那么长时间不使用的线程资源就应该销毁掉，这样就需要一个线程空闲时间的计数来描述线程何时被销毁；

5、前面描述过线程池也是有生命周期的，因此需要有一个状态来描述线程池当前的运行状态；

6、线程池的任务队列如果有边界，那么就需要有一个任务拒绝策略来处理过多的任务，同时在线程池的销毁阶段也需要有一个任务拒绝策略来处理新加入的任务；

7、上面种的线程池大小、线程空闲实际那、线程池运行状态等等状态改变都不是线程安全的，因此需要有一个全局的锁（mainLock）来协调这些竞争资源；

8、除了以上数据结构以外，ThreadPoolExecutor 还有一些状态用来描述线程池的运行计数，例如线程池运行的任务数、曾经达到的最大线程数，主要用于调试和性能分析。

## <a></a>构造函数

<pre>1
2
3
4
5
6
7
8
9
    public ThreadPoolExecutor(int corePoolSize,  //核心线程容量
                              int maximumPoolSize,//线程池的允许线程的最大容量
                              long keepAliveTime,//存活时间
                              TimeUnit unit,//时间单位
                              BlockingQueue<Runnable> workQueue,//任务队列，默认Executors.defaultThreadFactory()
                              ThreadFactory threadFactory) {//线程工程，
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
             threadFactory, defaultHandler);//
    }
</pre>

在构造函数中，里面有一个线程工厂 ThreadFactory，这里需要介绍下。

## <a></a>ThreadFactory 的介绍

既然是线程池，先看下线程是如何被创建出来的。

<pre>1
2
3
4
5
6
7
8
    public interface ThreadFactory {

        /*
         * Constructs a new {@code Thread}.  Implementations may also initialize
         * priority, name, daemon status, {@code ThreadGroup}, etc.
         */
        Thread newThread(Runnable r);
    }
</pre>

Thread 接口中只有一个 newThread 方法用于产生一个线程。其默认实现类为 DefaultThreadFactory 类，此类是在 Executors 类的内部类。

<pre>1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);//线程池标号
        private final ThreadGroup group;//线程组
        private final AtomicInteger threadNumber = new AtomicInteger(1);//线程标号
        private final String namePrefix;//

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                                  Thread.currentThread().getThreadGroup();
            namePrefix = "pool-" +
                          poolNumber.getAndIncrement() +
                         "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                                  namePrefix + threadNumber.getAndIncrement(),
                                  0);
            if (t.isDaemon())
                t.setDaemon(false);//所有线程都默认设置为非Daemon线程
            //产生的所有线程优先级相同
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }

</pre>

从上面代码中可以得到以下几点信息：

1、在这个线程工厂中，同一个线程池的所有线程属于同一个线程组，也就是创建线程池的那个线程组，
同时线程池的名称都是 “pool--thread-”，
其中 poolNum 是线程池的数量序号，threadNum 是此线程池中的线程数量序号。

2、另外对于线程池中的所有线程默认都转换为非后台线程，这样主线程退出时不会直接退出 JVM，而是等待线程池结束。

3、还有一点就是默认将线程池中的所有线程都调为同一个级别，这样在操作系统角度来看所有系统都是公平的，不会导致竞争堆积。

## <a></a>executor 方法

当我们使用 ThreadPoolExecutor 的构造方法构造出一个线程池后，我们将调用 executor 方法来执行任务。下面我们就来分析下 ThreadPoolExecutor 中 executor 的内部实现。

在 executor 方法上面有这样一段说明，如下：

<pre>1
2
3
4
5
6
      Executes the given task sometime in the future.  The task
      may execute in a new thread or in an existing pooled thread.

      If the task cannot be submitted for execution, either because this
      executor has been shutdown or because its capacity has been reached,
      the task is handled by the current {@code RejectedExecutionHandler}.
</pre>

上面的意思比较好理解，我们可以从中得到如下的信息

1、任务将在未来的某个时刻可能被执行，意味着可能不被执行，为什么又这个可能呢？下面再说。

2、任务可能在一个新线程中也可能在线程池中已存在的线程中执行

3、如果任务不能被提交执行，可能是由于线程池已经 shutdowm 或者是任务队列中已经满了。这种情况下任务将由此时的任务拒绝策略决定怎么来处理

这里来回答为什么会在未来的某个时刻被执行，意味着可能不被执行？

如果不被执行，可能是出现了上面所说的第 3 中的情况 (shutdown 或者是任务队列满了)，在未来被执行即延时执行可能因为需要等待线程来处理。

下面为 executor 方法的代码

<pre>1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
    public void execute(Runnable command) {
        if (command == null)
            throw new NullPointerException();
        /*
         * Proceed in 3 steps:
         *
         * 1\. If fewer than corePoolSize threads are running, try to
         * start a new thread with the given command as its first
         * task.  The call to addWorker atomically checks runState and
         * workerCount, and so prevents false alarms that would add
         * threads when it shouldn't, by returning false.
         *翻译：如果目前工作的线程小于核心线程的个数，则为新任务尝试的开启新的线程来处理。
         *且调用addWorker来自动检测runState和workerCount.防止本来不需要添加线程而要求添加线程的假警报
         * 2\. If a task can be successfully queued, then we still need
         * to double-check whether we should have added a thread
         * (because existing ones died since last checking) or that
         * the pool shut down since entry into this method. So we
         * recheck state and if necessary roll back the enqueuing if
         * stopped, or start a new thread if there are none.
         *
         * 3\. If we cannot queue task, then we try to add a new
         * thread.  If it fails, we know we are shut down or saturated
         * and so reject the task.
         */
        int c = ctl.get();
        /*
        如果此时的线程数小于核心线程数，
        则尝试的为任务command开启一个线程
        */
        if (workerCountOf(c) < corePoolSize) {
            if (addWorker(command, true))
                return;
            c = ctl.get();
        }
        //检测线程池是否处于工作状态且任务成功添加到阻塞队列
        if (isRunning(c) && workQueue.offer(command)) {
            int recheck = ctl.get();
            /*
                重新检测一遍，如果线程池没有工作，
            则考虑将任务从队列中移除，如果移除成功则根据任务拒绝策略类进行处理
            */
            if (! isRunning(recheck) && remove(command))
                reject(command);
            else if (workerCountOf(recheck) == 0)//如果工作线程数为0，则
                addWorker(null, false);
        }
        else if (!addWorker(command, false))//如果我们添加到队列失败，则尝试开启一个新的线程
            reject(command);
    }

    //判断线程池是否处于Running状态
    private static boolean isRunning(int c) {
        return c < SHUTDOWN;
    }
</pre>

executor 源码中的注释已经很明确的说明了 exector 方法的流程。这里我再梳理如下：

线程池的工作流程时这样的：

1、当我们提交一个任务，检测线程池中的工作线程是否小于核心线程数，如果小于，则进行 2，否则进行 3.

2、尝试建一个新的线程来将任务作为第一个任务来执行，如果建立成功，则立即返回。否则进行 3

3、检测线程池是否处于工作状态，如果正在工作，则进行 4，否则进行 10.

4、将任务加入到任务队列中，如果加入成功，则进行 5\. 否则，进行 10

5、重新检测线程池是否处于工作状态，如果不是，则进行 6，如果是，则进行 7

6、将任务从任务队列中移除，如果移除成功，则利用任务拒绝策略来处理该任务。否则进行 7.

7、检查工作线程数是否为零。如果不为零，则直接退出。否则进行 8.

8、添加一个新的线程且该线程的第一个任务为 null。无论是否成功均退出。

10、尝试建立一个新的线程来处理该任务，如果建立失败则利用任务拒绝策略来进行处理。

比较复杂哈，好好理解下，应该没什么问题。

上面的方法中调用了 addWorker 来添加新的线程来处理新的任务。下面我们就来看下这个方法的内部实现。

## <a></a>addWorker 方法

此函数的功能：

根据当前线程池的状态和给的边界条件来检测是否需要一个新的线程添加。
如果需要，则添加到线程队列中并调整工作线程数并启动线程执行第一个任务。
如果该方法检测到线程池处于 STOP 状态或者是察觉到将要停止，则返回 false。
如果线程工厂创建线程失败 (可能是由于发生了 OOM 异常) 则也返回 false。

按照上面的描述来看 addWorker 具体代码的实现就相当好理解了。哈，已有一定的注释，这里就不再过多的介绍了。

<pre>1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
55
56
57
58
59
60
61
62
63
64
65
66
67
68
69
70
71
72
73
74
75
76
77
78
79
80
81
82
    private boolean addWorker(Runnable firstTask, boolean core) {
        //先是各种检测来判断是否需要建立一个新的Worker对象
        retry:
        for (;;) {
            int c = ctl.get();
            int rs = runStateOf(c);

            // Check if queue empty only if necessary.
            if (rs >= SHUTDOWN &&
                ! (rs == SHUTDOWN &&
                   firstTask == null &&
                   ! workQueue.isEmpty()))
                return false;

            for (;;) {
                int wc = workerCountOf(c);
                if (wc >= CAPACITY ||
                    wc >= (core ? corePoolSize : maximumPoolSize))//大于边界就不创建
                    return false;
                if (compareAndIncrementWorkerCount(c))
                    break retry;//退出循环，即需要建立一个新的Worker对象
                c = ctl.get();  // Re-read ctl
                if (runStateOf(c) != rs)
                    continue retry;
                // else CAS failed due to workerCount change; retry inner loop
            }
        }

        //开始产生一个Worker对象，并将其添加到线程队列中去

        boolean workerStarted = false;
        boolean workerAdded = false;
        Worker w = null;
        try {
            w = new Worker(firstTask);
            final Thread t = w.thread;
            if (t != null) {
                final ReentrantLock mainLock = this.mainLock;
                mainLock.lock();
                try {
                    // Recheck while holding lock.
                    // Back out on ThreadFactory failure or if
                    // shut down before lock acquired.
                    int rs = runStateOf(ctl.get());

                    if (rs < SHUTDOWN ||
                        (rs == SHUTDOWN && firstTask == null)) {
                        if (t.isAlive()) // precheck that t is startable
                            throw new IllegalThreadStateException();
                        workers.add(w);//添加到线程队列中
                        int s = workers.size();
                        if (s > largestPoolSize)
                            largestPoolSize = s;
                        workerAdded = true;
                    }
                } finally {
                    mainLock.unlock();
                }
                if (workerAdded) {//添加成功，则该启动线程
                    t.start();
                    workerStarted = true;
                }
            }
        } finally {
            if (! workerStarted)//如果没有启动成功，执行addWorkerFailed方法
                addWorkerFailed(w);//功能：从线程队列中将其移除。
        }
        return workerStarted;
    }

    private void addWorkerFailed(Worker w) {
        final ReentrantLock mainLock = this.mainLock;
        mainLock.lock();
        try {
            if (w != null)
                workers.remove(w);
            decrementWorkerCount();
            tryTerminate();
        } finally {
            mainLock.unlock();
        }
    }
</pre>

最后我们就来看下线程类 Worker。

## <a></a>线程类 Worker

Worker 类主要维持线程执行任务时的中断控制状态，以及其他的一些小的记录。
Worker 类继承了 AQS 同步器，用于在执行任务前后来获取和释放锁。这可防止中断，在等待一个任务时
旨在唤醒工作线程而不是中断正在运行的任务。这里实现了一个简单的非重入独占锁而不是使用 ReentrantLock。
主要是因为不想工作任务正在执行线程池中的控制方法时能够被再次锁住。
除此之外，为了静止中断知道线程正在开始执行任务，我们将
锁的初始状态设置为一个负数，当启动时将在 runWorker 中清除。

源代码如下：

<pre>1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
55
56
57
58
59
60
61
62
63
64
65
66
67
68
69
70
71
    private final class Worker
        extends AbstractQueuedSynchronizer
        implements Runnable
    {
        /**
         * This class will never be serialized, but we provide a
         * serialVersionUID to suppress a javac warning.
         */
        private static final long serialVersionUID = 6138294804551838833L;

        /** Thread this worker is running in.  Null if factory fails. */
        final Thread thread;
        /** Initial task to run.  Possibly null. */
        Runnable firstTask;
        /** Per-thread task counter */
        volatile long completedTasks;

        /**
         * Creates with given first task and thread from ThreadFactory.
         * @param firstTask the first task (null if none)
         */
        Worker(Runnable firstTask) {
            setState(-1); // inhibit interrupts until runWorker
            this.firstTask = firstTask;
            this.thread = getThreadFactory().newThread(this);//利用线程工厂产生一个线程，可能失败
        }

        /** Delegates main run loop to outer runWorker  */
        public void run() {
            runWorker(this);//调用runWorker方法来执行这个任务
        }

        // Lock methods
        //
        // The value 0 represents the unlocked state.
        // The value 1 represents the locked state.

        protected boolean isHeldExclusively() {
            return getState() != 0;
        }

        protected boolean tryAcquire(int unused) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        protected boolean tryRelease(int unused) {
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        public void lock()        { acquire(1); }
        public boolean tryLock()  { return tryAcquire(1); }
        public void unlock()      { release(1); }
        public boolean isLocked() { return isHeldExclusively(); }

        void interruptIfStarted() {
            Thread t;
            if (getState() >= 0 && (t = thread) != null && !t.isInterrupted()) {
                try {
                    t.interrupt();
                } catch (SecurityException ignore) {
                }
            }
        }
    }

</pre>

以上就是关于 Worker 类的介绍。

当 Worker 类中的线程启动之后，则会调用 Worker 类中的 run 方法，而 run 方法调用了 runWorker 方法，因此，下面我们就看下 runWorker 方法

<pre>1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
    final void runWorker(Worker w) {
        Thread wt = Thread.currentThread();
        Runnable task = w.firstTask;
        w.firstTask = null;
        w.unlock(); // allow interrupts
        boolean completedAbruptly = true;
        try {
            while (task != null || (task = getTask()) != null) {
                w.lock();
                // If pool is stopping, ensure thread is interrupted;
                // if not, ensure thread is not interrupted.  This
                // requires a recheck in second case to deal with
                // shutdownNow race while clearing interrupt
                if ((runStateAtLeast(ctl.get(), STOP) ||
                     (Thread.interrupted() &&
                      runStateAtLeast(ctl.get(), STOP))) &&
                    !wt.isInterrupted())
                    wt.interrupt();
                try {
                    beforeExecute(wt, task);//在ThreadPoolExecutor是空实现
                    Throwable thrown = null;
                    try {
                        task.run();//重点
                    } catch (RuntimeException x) {
                        thrown = x; throw x;
                    } catch (Error x) {
                        thrown = x; throw x;
                    } catch (Throwable x) {
                        thrown = x; throw new Error(x);
                    } finally {
                        afterExecute(task, thrown);//在ThreadPoolExecutor是空实现
                    }
                } finally {
                    task = null;
                    w.completedTasks++;
                    w.unlock();
                }
            }
            completedAbruptly = false;
        } finally {
            processWorkerExit(w, completedAbruptly);
        }
    }
</pre>

上面方法抛出各种检查，简单来说就是：线程池正常工作的时候直接调用了我们自己提交的任务的 run 方法。上面代码的重点就是这句话： task.run();

下面我们配合一个例子来总结下使用线程池来执行任务的总流程

<pre>1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
    public class ExecutorDemo {

        private static  SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        public static void main(String[] args) {
            int corePoolSize = 1;
            int maximumPoolSize = 1;
            BlockingQueue queue = new  ArrayBlockingQueue<Runnable>(1);
            ThreadPoolExecutor pool = new ThreadPoolExecutor(corePoolSize,  maximumPoolSize, 
                    0, TimeUnit.SECONDS, queue ) ;
            pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            for(int i=0;i<10;i++){
                final int index = i;
                pool.submit(new Runnable(){

                    @Override
                    public void run() {
                        log(Thread.currentThread().getName()+"begin run task :"+index);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        log(Thread.currentThread().getName()+" finish run  task :"+index);
                    }

                });
            }

            log("main thread before sleep!!!");
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log("before shutdown()");

            pool.shutdown();

            log("after shutdown(),pool.isTerminated=" + pool.isTerminated());
            try {
                pool.awaitTermination(1000L, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log("now,pool.isTerminated=" + pool.isTerminated());
        }

        protected static void log(String string) {
            System.out.println(sdf.format(new Date())+"  "+string);
        }

    }
</pre>

1、第一步，当我们在程序中使用 submit 方法提交一个任务的时候，如果需要创建一个新的 Worker(线程) 来执行时，就调用线程工厂创建一个线程并和任务一起组合成为一个 Worker 对象。，同时将 Worker 对象添加到 Worker 工作队列中。需要说明的是，Worker 队列构造的时候带着一个任务 Runnable，因此 Worker 创建时总是绑定着一个待执行任务。

换句话说，创建线程的前提是有必要创建线程（任务数已经超出了线程或者强制创建新的线程，具体见前面的源码分析），不会无缘无故创建一堆空闲线程等着任务。这是节省资源的一种方式。

2、一旦线程池启动刚刚创建的 Worker 对象中包含的线程后，就会调用 Worker 对象中的 run 方法，那么线程工作队列 Worker 就从第 1 个任务开始执行，（这时候发现构造 Worker 时传递一个任务的好处了），一旦第 1 个任务执行完毕，就从线程池的任务队列中取出下一个任务进行执行。循环如此，直到线程池被关闭或者任务抛出了一个 RuntimeException。

由此可见，线程池的基本原理其实也很简单，无非预先启动一些线程，线程进入死循环状态，每次从任务队列中获取一个任务进行执行，直到线程池被关闭。如果某个线程因为执行某个任务发生异常而终止，那么重新创建一个新的线程而已。如此反复。

其实，线程池原理看起来简单，但是复杂的是各种策略，例如何时该启动一个线程，
何时该终止、挂起、唤醒一个线程，任务队列的阻塞与超时，线程池的生命周期以及任务拒绝策略等等。

## <a></a>小结

在看参考博客的时候，对线程池的理解还是懵懂懵懂的，写完这篇博客之后，发现对线程池的理解又多了一些。

线程池的工作原理：当我们写程序中使用 submit 往线程池中提交新任务的时候，线程池首先会判断是否需要生成新的线程来执行这个任务。如果需要，则新建一个，然后启动此线程，接着就是一个一个的任务被启动的线程执行了。以上。

## <a></a>参考资料

1、[http://www.blogjava.net/xylz/archive/2011/01/18/343183.html](http://www.blogjava.net/xylz/archive/2011/01/18/343183.html)