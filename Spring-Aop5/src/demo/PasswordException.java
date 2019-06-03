package demo;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/6/3 15:37
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/6/3 15:37
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */

/**
 * 运行时异常：可以通过编译也可以不处理，一般继承自RunTimeException
 * 编译时异常：受查异常，编译器过不了的异常
 */
public class PasswordException extends ExceptionHanld{
    public PasswordException() {
    }

    public PasswordException(String message) {
        super(message);
    }
}
