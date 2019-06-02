package demo;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.util.StringUtils;

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
public class MyMethodInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("执行环绕方法之前");
        Object result =  methodInvocation.proceed();
        System.out.println("执行环绕方法之后");
        if (!StringUtils.isEmpty(result)){
            return result.toString().toUpperCase();
        }
        return null;
    }
}
