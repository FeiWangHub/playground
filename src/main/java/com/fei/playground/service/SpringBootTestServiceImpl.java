package com.fei.playground.service;

import com.fei.playground.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
@Primary//主bean
public class SpringBootTestServiceImpl implements SpringBootTestService {

    @Autowired
    TestDao testDao;

    @Override
    public String helloWorld() {
        System.out.println("Inside SpringBootTestServiceImpl: Hello World!");
        return "hello world";
    }

    @PostConstruct
    public void init(){
        System.out.println("SpringBootTestService注入后初始化init成功");
    }

    @PreDestroy
    public void preDestroy(){
        System.out.println("SpringBootTestService preDestroy销毁成功");
    }

}
