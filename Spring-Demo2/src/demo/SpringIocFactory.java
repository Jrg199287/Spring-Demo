package demo;

/**
 * java类简单作用描述
 *
 * @Description: 创建bean工厂
 * @Author: 作者姓名
 * @CreateDate: 2019/5/15 21:46
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/5/15 21:46
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public  class SpringIocFactory {
    public  SpringTest getSpringFactory(){
        return  new SpringTestImpl();
    }

    public  static SpringTest  getSpring(){
        return  new SpringTestImpl();
    }
}
