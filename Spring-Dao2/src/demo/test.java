package demo;

import demo.exception.BuyStockException;
import demo.service.IBuyStockService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/5/22 22:38
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/5/22 22:38
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public class test {
    IBuyStockService sprinngTest =null;
    @Before
    public void before(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
         sprinngTest = (IBuyStockService) context.getBean("buyStockService");
    }
    @Test
    public void demo1(){
        sprinngTest.openAccount("ZZ",300000);
        sprinngTest.openStock("bbb",0);
    }
    @Test
    public void demo2(){
        try {
            sprinngTest.buystock("ZZ",10000,"bbb",4);
        } catch (BuyStockException e) {
            e.printStackTrace();
        }
    }
}
