package demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/5/18 11:41
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/5/18 11:41
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public class MybeanPostAccess  implements BeanPostProcessor {
    @Override
//    bean容器加载前方法
//    o --正在进行初始化的bean对象
//    s --表示正在初始化的bean对象的id
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("======bean容器加载前方法======");
        return o;
    }
//    bean容器加载后方法
    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        /*拦截到bean创建之后的对象并进行处理*/
        /*这里用到了代理模式和匿名内部类*/
        if("myservice".equals(s)){
            Object obj = Proxy.newProxyInstance(o.getClass().getClassLoader(),o.getClass().getInterfaces(), new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    if("doSome".equals(method.getName())){
                        return ((String)method.invoke(o,args)).toUpperCase();
                    }
                    return method.invoke(o,args);
                }
            });
            System.out.println("========bean容器加载后方法========");
            return obj;
        }else{
            System.out.println("========bean容器加载后方法========");
            return o;
        }
    }
}
