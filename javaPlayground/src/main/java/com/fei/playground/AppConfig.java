package com.fei.playground;

import com.fei.playground.service.SpringBootTestService;
import com.fei.playground.service.SpringBootTestServiceImpl;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan("com.fei.playground")
@Configuration
public class AppConfig {

    public static void main(String[] args) {
//        aopTest();
    }

    //手动创建AOP代理
    public static void aopTest(){
        //配置代理
        SpringBootTestService target = new SpringBootTestServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvice(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.out.println("--AOP ProxyFactory Before---");
                Object result = invocation.proceed();//实际就是执行 target.helloWorld();
                System.out.println("--AOP ProxyFactory After---");
                return result;
            }
        });

        //创建代理对象
        SpringBootTestService springBootTestService = (SpringBootTestService) proxyFactory.getProxy();
        springBootTestService.helloWorld();
    }

    //定义方法拦截器为bean
    @Bean
    public MethodInterceptor feiAroundAOPAdvice(){
        return new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.out.println("--AOP feiAroundAdvice Before---");
                Object result = invocation.proceed();//实际就是执行 target.xxxxx();
                System.out.println("--AOP feiAroundAdvice After---");
                return result;
            }
        };
    }

    //自动根据bean名字添加aop代理
    @Bean
    public BeanNameAutoProxyCreator beanNameAutoProxyCreator(){
        BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
        beanNameAutoProxyCreator.setBeanNames("springBootTest*");//定义要拦截的bean
        beanNameAutoProxyCreator.setInterceptorNames("feiAroundAOPAdvice");//拦截器
        return beanNameAutoProxyCreator;
    }

    //根据pointcut执行代理
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor(){
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.addMethodName("pointCut");//定义pointcut方法名字

        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        defaultPointcutAdvisor.setPointcut(pointcut);

        defaultPointcutAdvisor.setAdvice(new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.out.println("--AOP defaultPointcutAdvisor:pointCut Before---");
                Object result = invocation.proceed();//实际就是执行 target.xxxxx();
                System.out.println("--AOP defaultPointcutAdvisor:pointCut After---");
                return result;
            }
        });

        return defaultPointcutAdvisor;
    }

    //pointCut advisor 处理器创建，配置defaultPointcutAdvisor使用，也可以使用import创建这个bean
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }

}
