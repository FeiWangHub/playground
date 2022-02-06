package com.fei.playground.controller;

import com.fei.playground.dao.UserMapper;
import com.fei.playground.service.SpringBootTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    SpringBootTestService springBootTestService;
    @Autowired
    UserMapper userMapper;

    @ResponseBody
    @RequestMapping("/hello")
    public String helloWorld(){
        System.out.println("print " + springBootTestService);
        return springBootTestService.helloWorld();
    }

    @ResponseBody
    @RequestMapping("/pointCut")
    public String pointCut(){
        return springBootTestService.helloWorld();
    }

    @ResponseBody
    @RequestMapping("/testMybatis")
    public String testMybatis(){
        return userMapper.selectAllUser().toString();
    }
}
