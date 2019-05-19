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
     * 生命销毁时候一定要关闭容器
     */
    @Test
    public void Demo2(){
        //创建容器对象加载spring的配置文件
        //项目配置文件过多时
        ApplicationContext context = new ClassPathXmlApplicationContext
                ("springContext.xml");
        Student  sprinngTest = (Student) context.getBean("student");
        Student  sprinngTest1 = (Student) context.getBean("student1");
        Student  sprinngTest2 = (Student) context.getBean("student2");
        Student  sprinngTest3 = (Student) context.getBean("student3");
        System.out.println( sprinngTest);
        System.out.println( sprinngTest1);
        System.out.println( sprinngTest2);
        System.out.println( sprinngTest3);
    }


}
