package com.fei.graphql.dgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DgsApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DgsApplication.class, args);
    }

}
