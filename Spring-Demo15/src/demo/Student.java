package demo;

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
public class Student {
    private String name;
    private Integer age;
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
