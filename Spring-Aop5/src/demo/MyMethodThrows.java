package demo;
import org.springframework.aop.ThrowsAdvice;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/5/22 23:04
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/5/22 23:04
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public class MyMethodThrows implements ThrowsAdvice {
    public void afterThrowing(UsernameException ex){
        System.out.println("出现异常："+ex.getMessage());
    }
    public void afterThrowing(PasswordException ex){
        System.out.println("出现异常："+ex.getMessage());
    }
    public void afterThrowing(Exception ex){
        System.out.println("出现其他异常："+ex.getMessage());
    }
}
