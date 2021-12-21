package com.fei.playground.service;

import com.fei.playground.dao.TestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    TestDao testDao;

    @Override
    public String helloWorld() {
        return "hello world";
    }

}
