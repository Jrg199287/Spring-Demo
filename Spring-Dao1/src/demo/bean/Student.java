package demo.bean;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/6/8 18:23
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/6/8 18:23
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public class Student {
    private Integer id;
    private String name;
    private Integer age;
    private Integer score;
    private Integer sex;

    public Student(String name, Integer age, Integer score, Integer sex) {
        this.name = name;
        this.age = age;
        this.score = score;
        this.sex = sex;
    }

    public Student() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", sex='" + sex + '\'' +
                '}';
    }
}
