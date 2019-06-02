package demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/5/22 22:38
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/5/22 22:38
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public class test {
    public static void main(String[] args) {
        //代理模式
        SpringTest target = new SpringTestImpl();
        SpringTest springTest = (SpringTest) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                ServiceUtils.doTransation();
                Object result = method.invoke(target,args);
                ServiceUtils.doLogs();
                return null;
            }
        });
        springTest.doFirst();
        System.out.println("=================");
        springTest.doSecond();
    }
}
