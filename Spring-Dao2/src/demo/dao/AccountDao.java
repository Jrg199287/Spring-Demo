package demo.dao;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/6/9 11:38
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/6/9 11:38
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public interface AccountDao {
    void insertAcount(String aname,double money);
    void updateAcount(String aname,double money,boolean isbuy);
}
