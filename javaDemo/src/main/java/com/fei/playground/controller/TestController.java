package com.fei.playground.controller;

import com.fei.playground.dao.UserMapper;
import com.fei.playground.service.SpringBootTestService;
import com.fei.playground.util.I18nUtil;
import com.fei.playground.util.ResourceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Locale;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    SpringBootTestService springBootTestService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    ApplicationContext appCtx;

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
    @RequestMapping("/i18n")
    public String i18n() throws IOException {
        //TODO 没配置好
        return I18nUtil.get("shen5", new Locale("zh_CN"));
    }

    @ResponseBody
    @RequestMapping("/testMybatis")
    public String testMybatis(){
        return userMapper.selectAllUser().toString();
    }
}
