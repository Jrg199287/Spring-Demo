package demo;


import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名:焦荣国
 * @CreateDate: 2019/5/12 12:17
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/5/12 12:17
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public class SpringIocDemo1 {
    /**
     *
     * 静态工厂
     */
    @Test
    public void Demo1(){
        //创建容器对象加载spring的配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringTest sprinngTest = (SpringTest) context.getBean("myservice");
        System.out.println(sprinngTest.doSome());
        SpringTest sprinng = (SpringTest) context.getBean("myservice2");
        System.out.println(sprinng.doSome());
       // SpringTest sprinngTest2 = (SpringTest) context.getBean("myservice3");
       // SpringTest sprinngTest3 = (SpringTest) context.getBean("myservice3");
       // System.out.println( sprinngTest2==sprinngTest3);
    }




}
