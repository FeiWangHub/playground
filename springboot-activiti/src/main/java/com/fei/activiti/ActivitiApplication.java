package com.fei.activiti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ActivitiApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(ActivitiApplication.class, args);
    }

}
