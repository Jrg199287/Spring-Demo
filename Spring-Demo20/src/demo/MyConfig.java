package demo;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/5/19 22:43
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/5/19 22:43
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
@Configuration
public class MyConfig {
    @Bean(value = "mySchool",autowire = Autowire.BY_NAME )
    public School mySchoolCreator(){
        return new School("北京大学","北京");
    }
    @Bean(value = "myStudent",autowire = Autowire.BY_TYPE)
    public Student myStudentCreator(){
        return new Student("北京大学",22,"北京");
    }


    @Bean(value = "myservice",autowire = Autowire.BY_TYPE)
    public SpringTestImpl myTestCreator(){
        return new SpringTestImpl();
    }


}
