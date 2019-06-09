package demo.exception;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/6/9 12:17
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/6/9 12:17
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public class BuyStockException extends Exception{
    public BuyStockException() {
    }

    public BuyStockException(String message) {
        super(message);
    }
}
