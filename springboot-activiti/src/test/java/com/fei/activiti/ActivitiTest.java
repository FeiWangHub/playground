package com.fei.activiti;

import org.activiti.engine.RepositoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ActivitiTest {

    @Autowired
    RepositoryService repositoryService;

    @Test
    public void test(){
        System.out.println("test");
        System.out.println(repositoryService);
    }

}
