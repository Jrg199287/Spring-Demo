package demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/5/19 12:58
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/5/19 12:58
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
@Repository
public class Student {
    @Value("张三")
    private String name;
    @Value("22")
    private Integer age;
    @Value("街道")
    private String dress;
    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDress() {
        return dress;
    }

    public void setDress(String dress) {
        this.dress = dress;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", dress='" + dress + '\'' +
                '}';
    }


}
