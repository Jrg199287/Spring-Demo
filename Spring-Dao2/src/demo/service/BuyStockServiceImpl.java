package demo.service;

import demo.dao.AccountDao;
import demo.dao.StockDao;
import demo.exception.BuyStockException;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/6/9 11:14
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/6/9 11:14
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public class BuyStockServiceImpl implements IBuyStockService {
    private AccountDao adao;
    private StockDao sdao;

    public void setAdao(AccountDao adao) {
        this.adao = adao;
    }

    public void setSdao(StockDao sdao) {
        this.sdao = sdao;
    }

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public void openAccount(String aname, double money) {
        adao.insertAcount(aname,money);
    }
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    @Override
    public void openStock(String sname, int amount) {
        sdao.insertStock(sname,amount);
    }
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED,rollbackFor=BuyStockException.class)
    @Override
    public void buystock(String aname, double money, String sname, int amount) throws BuyStockException {
        boolean isbuy = true;
        adao.updateAcount(aname,money,isbuy);
        if(money>0){
            throw new BuyStockException("购买异常！");
        }
        sdao.updateStock(sname,amount,isbuy);
    }
}
