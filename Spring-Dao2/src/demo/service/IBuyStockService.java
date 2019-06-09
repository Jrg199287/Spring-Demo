package demo.service;

import demo.exception.BuyStockException;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/6/9 11:12
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/6/9 11:12
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public interface IBuyStockService {
    void openAccount(String aname,double money);
    void openStock(String sname,int amount);
    void buystock(String aname,double money,String sname,int amount)throws BuyStockException;
}
