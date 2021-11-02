package com.nextdoorwang.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "Fei") String name) {
        return "hello " + name + ", welcome to spring boot";
    }

    private static final String template = "Hello, the %s Fei!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public GreetingResponse greeting(@RequestParam(value = "title", defaultValue = "Bachata King") String title) {
        return new GreetingResponse(counter.incrementAndGet(), String.format(template, title));
    }
}
