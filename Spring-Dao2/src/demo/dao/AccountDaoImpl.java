package demo.dao;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/6/9 11:40
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/6/9 11:40
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public class AccountDaoImpl extends JdbcDaoSupport implements AccountDao{
    @Override
    public void insertAcount(String aname, double money) {
        String sql="insert into account (aname,banlance) values(?,?)";
        int result = this.getJdbcTemplate().update(sql,aname,money);
    }

    @Override
    public void updateAcount(String aname, double money, boolean isbuy) {
        String sql = "update account set banlance=banlance+? where aname = ?";
        if(isbuy){
             sql = "update account set banlance=banlance-? where aname = ?";
        }
        int result = this.getJdbcTemplate().update(sql,money,aname);
    }
}
