package demo;

import demo.bean.Student;
import demo.service.IStudentService;
import demo.service.StudentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

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
    IStudentService sprinngTest =null;
    @Before
    public void before(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
         sprinngTest = (IStudentService) context.getBean("studentService");
    }
    @Test
    public void demo1(){
        Student student = new Student("测试1",20,90,0);
        sprinngTest.add(student);
    }
    @Test
    public void demo2(){
       sprinngTest.removeById(2);
    }
    @Test
    public void demo3(){
        Student student = new Student("222",20,90,0);
        student.setId(3);
        sprinngTest.update(student);
    }
    @Test
    public void demo4(){
        List<String> result =  sprinngTest.queryNames();
        System.out.println(result);
    }
    @Test
    public void demo5(){
        List<Student> result =sprinngTest.queryStudents();
        for ( Student student: result) {
            System.out.println(student);
        }
    }
}
