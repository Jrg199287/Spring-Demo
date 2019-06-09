package demo.dao;

import demo.bean.Student;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/6/9 8:51
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/6/9 8:51
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public class StudentRowMaper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setAge(resultSet.getInt("age"));
        student.setName(resultSet.getString("name"));
        student.setScore(resultSet.getInt("score"));
        student.setSex(resultSet.getInt("sex"));
        return student;
    }
}
