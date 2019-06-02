package demo;


import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

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
    SprinngTest sprinngTest ;

    /**
     * 面向接口开发耦合度很高
     */
    @Test
    public void Demo1(){
        sprinngTest = new SpringTestImpl();
        System.out.println(sprinngTest.doSome());
    }


    /**
     * ClassPathXmlApplicationContext
     * 会去系统的类目录下找对对应的配置文件
     */
    @Test
    public void Demo2(){
        //创建容器对象加载spring的配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SprinngTest  sprinngTest = (SprinngTest) context.getBean("myservice");
        System.out.println( sprinngTest.doSome());
    }

    /**
     * FileSystemXmlApplicationContext
     * 去系统的根目录找对应的配置文件
     */
    @Test
    public void Demo3(){
        //创建容器对象加载spring的配置文件
        ApplicationContext context = new FileSystemXmlApplicationContext("applicationContext.xml");
        SprinngTest  sprinngTest = (SprinngTest) context.getBean("myservice");
        System.out.println( sprinngTest.doSome());
    }

    /**
     *  BeanFactory用源码的底层也是可以做的
     */
    @Test
    public void Demo4(){
        //创建容器对象加载spring的配置文件
        BeanFactory app  = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
        SprinngTest  sprinngTest = (SprinngTest) app.getBean("myservice");
        System.out.println( sprinngTest.doSome());
    }

}
