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
public class StudentDaoImpl extends JdbcDaoSupport implements StudentDao {
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
        String sql = "update student set name=? where id = ?";
        int result = this.getJdbcTemplate().update(sql,student.getName(),student.getId());
        return result;
    }

    @Override
    public List<Student> selectStudents() {
        String sql = "select * from student";
        List<Student> result = this.getJdbcTemplate().query(sql,new StudentRowMaper());
        return result;
}

    @Override
    public Student selectById(int id) {
        String sql = "select * from student where id = ?";
        Student result = this.getJdbcTemplate().queryForObject(sql,new StudentRowMaper(),id);
        return result;
    }

    @Override
    public List<String> selectNames() {
        String sql = "select name from student";
        List<String> result = this.getJdbcTemplate().queryForList(sql,String.class);
        return result;
    }

    @Override
    public String selectNameById(int id) {
        String sql = "select name from student where id = ?";
        String result = this.getJdbcTemplate().queryForObject(sql,String.class,id);
        return result;
    }
}
