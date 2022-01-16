package com.canva.interview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/canva")
public class CanvaController {

    @RequestMapping("/hello")
    @ResponseBody
    public String helloWorld(){
        return "Hello Canva World";
    }

}
