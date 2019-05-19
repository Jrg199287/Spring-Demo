package demo;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/5/19 9:27
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/5/19 9:27
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public class School {
    private String name;
    private String adress;

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }

    public  String initAdress(){
        return adress + "ceshi";
    }
}
