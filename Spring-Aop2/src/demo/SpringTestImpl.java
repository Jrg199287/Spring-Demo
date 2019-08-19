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

public class SpringTestImpl implements SpringTest {
    @Override
    public String doFirst() {
        System.out.println("执行方法");
        return "执行了前置";
    }
    @Override
    public String doSecond() {
        System.out.println("执行方法");
        return "执行了后置";
    }
}
