package demo;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
    @Test
    public  void ssst() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringTest  sprinngTest = (SpringTest) context.getBean("myService");
        System.out.println( "输出结果"+sprinngTest.doFirst());
        System.out.println( "===================");
        System.out.println( "输出结果"+sprinngTest.doSecond());
        System.out.println( "===================");
        System.out.println( "输出结果"+sprinngTest.doThird());
    }
}
