package demo.bean;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/6/9 11:04
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/6/9 11:04
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public class Account {
    private Integer aid;
    private String aname;
    private Double banlance;

    public Account() {
    }

    public Account(String aname, Double banlance) {
        this.aname = aname;
        this.banlance = banlance;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public Double getBanlance() {
        return banlance;
    }

    public void setBanlance(Double banlance) {
        this.banlance = banlance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                ", banlance=" + banlance +
                '}';
    }
}
