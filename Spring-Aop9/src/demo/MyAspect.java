package demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * java类简单作用描述
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
@Aspect //表示当前类为切面
@Component
public class MyAspect {
    /*前置方法*/
    @Before("execution(* *..SpringTest.doFirst(..))")
    public void before(){
        System.out.println("执行前置通知!!!");
    }
    /*显示匹配方法*/
    @Before("execution(* *..SpringTest.doFirst(..))")
    public void before(JoinPoint joinPoint){
        System.out.println("执行前置通知!!! jp:"+joinPoint);
    }
    /*执行后置方法*/
    @AfterReturning(value = "execution(* *..SpringTest.doSecond(..))", returning = "result")
    public void after(JoinPoint joinPoint,Object result){
        System.out.println("执行后置方法!!! jp:"+joinPoint+"====result:"+result);
    }
    /*执行环绕方法*/
    @Around(value = "execution(* *..SpringTest.doSecond(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        return  result.toString().toUpperCase();
    }
    /*执行异常方法*/
    @AfterThrowing(value = "execution(* *..SpringTest.doThird(..))",throwing = "ex")
    public void exception(Exception ex) throws Throwable {
        System.out.println("执行异常方法!!!ex:"+ex);
    }
    /*执行最终方法*/
    @After(value = "execution(* *..SpringTest.doThird(..))")
    public void after() {
        System.out.println("执行最终通知方法!!!");
    }
    /*执行最终方法*/
    @After(value = "test()")
    public void after2() {
        System.out.println("执行最终通知方法!!!");
    }
    /*自定义一个切入点*/
    /*执行异常方法*/
    @Pointcut("execution(* *..SpringTest.doThird(..))")
    public void test(){}
}
