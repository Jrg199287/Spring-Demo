package demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

/**
 * java类简单作用描述
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
@Aspect
public class MyAspect {
    /*前置方法*/
    /*显示匹配方法*/
    public void before(JoinPoint joinPoint){
        System.out.println("执行前置通知!!! jp:"+joinPoint);
    }
    /*执行后置方法*/
    public void after(JoinPoint joinPoint,Object result){
        System.out.println("执行后置方法!!! jp:"+joinPoint+"====result:"+result);
    }
    /*执行环绕方法*/
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        return  result.toString().toUpperCase();
    }
    /*执行异常方法*/
    public void exception(Exception ex) throws Throwable {
        System.out.println("执行异常方法!!!ex:"+ex);
    }
    /*执行最终方法*/
    public void after3() {
        System.out.println("执行最终通知方法!!!");
    }
}
