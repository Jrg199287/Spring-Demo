package demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @Author: 作者姓名
 * @CreateDate: 2019/5/18 11:41
 * @UpdateUser: jiaorongguo
 * @UpdateDate: 2019/5/18 11:41
 * @Version: 1.0
 * 身无彩凤双飞翼，心有灵犀一点通。
 */
public class MybeanPostAccess  implements BeanPostProcessor {
    @Override
//    bean容器加载前方法
//    o --正在进行初始化的bean对象
//    s --表示正在初始化的bean对象的id
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("======bean容器加载前方法======");
        return o;
    }
//    bean容器加载后方法
    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("========bean容器加载后方法========");
        return o;
    }
}
