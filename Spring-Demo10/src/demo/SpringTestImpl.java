package demo;

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
public class SpringTestImpl{
    private String name;
    private Integer age;
    private School school; //对象属性也叫域属性

    public SpringTestImpl() {
    }

    public SpringTestImpl(String name, Integer age, School school) {
        this.name = name;
        this.age = age;
        this.school = school;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "SpringTestImpl{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school=" + school +
                '}';
    }

}
