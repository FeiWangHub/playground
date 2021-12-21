package com.fei.playground.controller;

import com.fei.playground.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    TestService testServcie;

    @ResponseBody
    @RequestMapping("/hello")
    public String helloWorld(){
        return testServcie.helloWorld();
    }

}
