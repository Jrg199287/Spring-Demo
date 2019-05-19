package demo;

import java.util.*;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/5/12 12:15
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/5/12 12:15
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public class SpringTestImpl{
     private School [] schools;
     private String [] msg;
     private List<String> lists;
     private Set<String> sets;
     private Map<String,Object> maps;
     private Properties properties;

    public void setSchools(School[] schools) {
        this.schools = schools;
    }

    public void setMsg(String[] msg) {
        this.msg = msg;
    }

    public void setLists(List<String> lists) {
        this.lists = lists;
    }

    public void setSets(Set<String> sets) {
        this.sets = sets;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }


    @Override
    public String toString() {
        return "SpringTestImpl{" +
                "schools=" + Arrays.toString(schools) +
                ", msg=" + Arrays.toString(msg) +
                ", lists=" + lists +
                ", sets=" + sets +
                ", maps=" + maps +
                ", properties=" + properties +
                '}';
    }
}
