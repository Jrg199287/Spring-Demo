package demo.service;

import demo.bean.Student;
import demo.dao.StudentDaoImpl;

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
public class StudentServiceImpl implements IStudentService{
    private StudentDaoImpl dao;

    @Override
    public int add(Student student) {
        int result  = dao.insert(student);
        return result;
    }

    @Override
    public int removeById(Integer id) {
        return dao.deleteById(id);
    }

    @Override
    public int update(Student student) {
        return dao.update(student);
    }

    @Override
    public List<Student> queryStudents() {
        return dao.selectStudents();
    }

    @Override
    public Student queryById(String id) {
        return dao.selectById(id);
    }

    @Override
    public List<String> queryNames() {
        return dao.selectNames();
    }

    @Override
    public String queryNameById(String id) {
        return dao.selectNameById(id);
    }

    public void setDao(StudentDaoImpl dao) {
        this.dao = dao;
    }

    public StudentDaoImpl getDao() {
        return dao;
    }
}
