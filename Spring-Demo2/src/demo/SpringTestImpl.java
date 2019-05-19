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
    public SpringTestImpl(){
        System.out.println("执行无参构造器");
    }
    @Override
    public String doSome() {
        return "干了什么事情!!!";
    }
}
