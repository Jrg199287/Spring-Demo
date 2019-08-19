package demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

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

@Repository
public class School {
    @Value("北京大学")
    private String name;
    @Value("北京")
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
