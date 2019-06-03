package demo;

import org.springframework.util.StringUtils;

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

public class SpringTestImpl implements SpringTest {
    @Override
    public String login(String username, String password) throws ExceptionHanld {
        if(StringUtils.isEmpty(username)){
            throw new UsernameException("用户名输入错误");
        }
        if(StringUtils.isEmpty(password)){
            throw new PasswordException("用户密码输入错误");
        }
        int a  = 3/0;
        return "成功";
    }
}
