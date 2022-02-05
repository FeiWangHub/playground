package com.fei.playground;

import com.fei.playground.model.TestFactoryBean;
import com.fei.playground.service.SpringBootTestService;
import com.fei.playground.service.SpringBootTestServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.function.Supplier;

@SpringBootApplication
@MapperScan(basePackages = {"com.fei.playground.dao"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

//        //手动获取Spring创建的bean
//        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);
//        SpringBootTestService test = (SpringBootTestService) appContext.getBean(SpringBootTestService.class);
//        //SpringBootTestService test = (SpringBootTestService) appContext.getBean("springBootTestServiceImpl");
//        System.out.println("1 手动获取Spring创建的bean: " + test.helloWorld());
//
//        //手动把某class注册为bean
//        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition();
//        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
//        beanDefinition.setBeanClass(SpringBootTestServiceImpl.class);
//        appContext.registerBeanDefinition("mySpringTestBean", beanDefinition);
//
//        SpringBootTestService mySpringTestBean = (SpringBootTestService) appContext.getBean("mySpringTestBean");
//        System.out.println("2 mySpringTestBean: " + mySpringTestBean.helloWorld());
//
//        //手动用AppContext注册bean
//        appContext.registerBean("appCtxSpringTestBean", SpringBootTestService.class, new Supplier<SpringBootTestService>() {
//            @Override
//            public SpringBootTestService get() {
//                return new SpringBootTestServiceImpl();
//            }
//        });
//        SpringBootTestService appCtxSpringTestBean = (SpringBootTestService) appContext.getBean("appCtxSpringTestBean");
//        System.out.println("3 appCtxSpringTestBean: " + appCtxSpringTestBean.helloWorld());
//
//        //Factory Bean 注册bean
//        TestFactoryBean testFactoryBean = (TestFactoryBean) appContext.getBean("testFactoryBean");
//        System.out.println("4 Test Factory Bean: " + testFactoryBean.helloFactoryBean());


    }
}