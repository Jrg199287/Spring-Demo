package demo.service;

import demo.bean.Student;

import java.util.List;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/6/8 18:25
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/6/8 18:25
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public interface IStudentService {
    int  add(Student student);
    int  removeById(Integer id);
    int update(Student student);
    List<Student> queryStudents();
    Student queryById(int id);
    List<String> queryNames();
    String queryNameById(int id);

}
