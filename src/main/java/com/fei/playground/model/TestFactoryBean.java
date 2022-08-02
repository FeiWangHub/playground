package com.fei.playground.model;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.SmartFactoryBean;
import org.springframework.stereotype.Component;

/**
 * 使用FactoryBean注册bean
 */
@Component
public class TestFactoryBean implements SmartFactoryBean {

    public String helloFactoryBean(){
        return "helloFactoryBean";
    }

    @Override
    public Object getObject() throws Exception {
        return new TestFactoryBean();
    }

    @Override
    public Class<?> getObjectType() {
        return TestFactoryBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public boolean isEagerInit() {
        return SmartFactoryBean.super.isEagerInit();
    }
}
