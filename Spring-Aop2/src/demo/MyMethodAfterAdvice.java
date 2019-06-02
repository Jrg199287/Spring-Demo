package demo;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/5/22 23:04
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/5/22 23:04
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public class MyMethodAfterAdvice implements AfterReturningAdvice {
/*
* Object o 目标方法的返回值
* Method method 目标方法
* Object[] objects 目标方法的参数
* Object o1 目标方法的对象
* */
    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("执行后置方法");
    }
}
