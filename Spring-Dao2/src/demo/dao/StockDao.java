package demo.dao;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/6/9 11:35
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/6/9 11:35
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public interface StockDao {
    void insertStock(String sname,double amount);
    void updateStock(String sname,int amount,boolean isbuy);
}
