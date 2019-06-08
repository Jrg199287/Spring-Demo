package demo.dao;

import demo.bean.Student;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/6/8 18:38
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/6/8 18:38
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public class StudentDaoImpl extends JdbcDaoSupport implements StudentMapper{
    @Override
    public int insert(Student student) {
        String sql="insert into student (name,age,score,sex) values(?,?,?,?)";
        int result = this.getJdbcTemplate().update(sql,student.getName(),student.getAge(),student.getScore(),student.getSex());
        return result;
    }

    @Override
    public int deleteById(Integer id) {
        String sql="delete from student where id= ?";
        int result = this.getJdbcTemplate().update(sql,id);
        return result;
    }

    @Override
    public int update(Student student) {
        String sql = "update student set (name,age,score,sex) values(?,?,?,?) where id = ?";
        int result = this.getJdbcTemplate().update(sql,student.getName(),student.getAge(),student.getScore(),student.getSex(),student.getId());
        return 0;
    }

    @Override
    public List<Student> selectStudents() {
        return null;
    }

    @Override
    public Student selectById(String id) {
        return null;
    }

    @Override
    public List<String> selectNames() {
        return null;
    }

    @Override
    public String selectNameById(String id) {
        return null;
    }
}
