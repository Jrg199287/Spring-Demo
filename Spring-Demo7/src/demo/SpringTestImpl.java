package demo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;

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
public class SpringTestImpl implements SprinngTest,BeanNameAware,BeanFactoryAware,InitializingBean,DisposableBean{
    //bean的生命周期的11个节点
    private String adao;
    public  SpringTestImpl(){
        System.out.println("step1:无参构造器");
    }
    public void setAdao(String adao) {
        System.out.println("step2:执行setter");
        this.adao = adao;
    }
    @Override
    public String doSome() {
        return "step9:具体的实现aaa";
    }
    @Override
    public void doStart() {
        System.out.println("step7: 初始化完毕后生命起始");
    }
    @Override
    public void doEnd() {
        System.out.println("step11：bean销毁生命结束");
    }
    @Override
    public void setBeanName(String s) {
        System.out.println("step3:获取bean的id ="+s);
    }
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("step4:获取bean的factory容器："+beanFactory);
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("step6: 初始化bean已经完毕了");
    }
    @Override
    public void destroy() throws Exception {
        System.out.println("step10: bean销毁之前");
    }
}
