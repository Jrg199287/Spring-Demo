package demo.dao;

import demo.bean.Student;

import java.util.List;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/6/8 18:29
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/6/8 18:29
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public interface StudentDao {
    int  insert(Student student);
    int  deleteById(Integer id);
    int update(Student student);
    List<Student> selectStudents();
    Student selectById(int id);
    List<String> selectNames();
    String selectNameById(int id);

}
