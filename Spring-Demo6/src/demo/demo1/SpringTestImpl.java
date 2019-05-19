package demo.demo1;

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

public class SpringTestImpl implements SprinngTest{
    @Override
    public String doSome() {
        return "aaa";
    }
    @Override
    public String doSome2() {
        return "DDD";
    }
    @Override
    public void doStart() {
        System.out.println("bbb");
    }

    @Override
    public void doEnd() {
        System.out.println("ccc");
    }
}
