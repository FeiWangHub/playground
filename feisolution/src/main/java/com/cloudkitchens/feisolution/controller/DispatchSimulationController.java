package com.cloudkitchens.feisolution.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dispatchSimulation")
public class DispatchSimulationController {

    @RequestMapping("/test")
    public String test(){
        return "hello, world";
    }

}
