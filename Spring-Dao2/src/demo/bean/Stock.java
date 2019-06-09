package demo.bean;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/6/9 11:06
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/6/9 11:06
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public class Stock {
    private Integer sid;
    private String sname;
    private int account;

    public Stock() {
    }

    public Stock(String sname, int account) {
        this.sname = sname;
        this.account = account;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", account=" + account +
                '}';
    }
}
