package demo;

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
public class MyMethodBeforeAdvice  implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println("执行前置方法");
    }
}
