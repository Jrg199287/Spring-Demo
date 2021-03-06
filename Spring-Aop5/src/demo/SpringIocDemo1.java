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
     * ClassPathXmlApplicationContext
     * 会去系统的类目录下找对对应的配置文件
     */
    @Test
    public void Demo2() {
        //创建容器对象加载spring的配置文件
        ApplicationContext context = new
        ClassPathXmlApplicationContext("applicationContext.xml");
        SpringTest  springTest = (SpringTest) context.getBean("myProfex");
        try {
            System.out.println( springTest.login("admin","123"));
        } catch (ExceptionHanld exceptionHanld) {
            exceptionHanld.printStackTrace();
        }
    }


}
