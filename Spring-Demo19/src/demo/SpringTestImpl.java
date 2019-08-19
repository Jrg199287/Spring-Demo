package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/5/12 12:15
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/5/12 12:15
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
//与Component功能相同但是意义不同的注解还有三个：
//    1.@Repository注解一边用在dao实现上
//    2.@Service注解一般用在service实现上
//    3.@Controller一般用在controller层
//@Scope 默认是singleton
@Component(value = "springtest")
public class SpringTestImpl {
    //第一种方式
   /* //域属性注入的两种注解
    @Autowired  //默认byType的方式自动注入
    private School schools;*/
   //第二种方式
    @Autowired(required = false)
    @Qualifier("student") //byName的方式自动注入要@Autowired和@Qualifier("student")两个注解同时存在
    private Student students;
    //第三种方式
    /*@Resource(name = "school")  //Resource的方式默认是以自动注入==byName
    private School schools;*/
    //第四种方式
    @Resource  //Resource的方式自动注入==byType
    private School schools;

    public void setSchools(School schools) {
        this.schools = schools;
    }

    public void setStudents(Student students) {
        this.students = students;
    }

    @PostConstruct
    public  void initBean(){
        System.out.println("当前bean被初始化");
    }
    @PreDestroy
    public  void destroyBean(){
        System.out.println("当前bean即将被销毁");
    }

    @Override
    public String toString() {
        return "SpringTestImpl{" +
                "schools=" + schools +
                ", students=" + students +
                '}';
    }
}
