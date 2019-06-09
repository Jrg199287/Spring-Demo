package demo.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/6/9 11:48
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/6/9 11:48
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public class StockDaoImpl extends JdbcDaoSupport implements StockDao {
    @Override
    public void insertStock(String sname, double amount) {
        String sql="insert into stock (sname,account) values(?,?)";
        int result = this.getJdbcTemplate().update(sql,sname,amount);
    }

    @Override
    public void updateStock(String sname, int amount, boolean isbuy) {
        String sql = "update stock set account=account-? where sname = ?";
        if(isbuy){
            sql = "update stock set account=account+? where sname = ?";
        }
        int result = this.getJdbcTemplate().update(sql,amount,sname);
    }
}
