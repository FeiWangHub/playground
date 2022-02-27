package com.fei.playground.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * SpringBoot自带的文件、网络资源加载器
 */
@Component
public class ResourceUtil {

    @Autowired
    private static ApplicationContext applicationContext;

    public Resource getWeb(String url){
        return applicationContext.getResource(url);
    }

    public Resource getFile(String path){
        return applicationContext.getResource("file:///"+path);// Users/Fei/xxx
    }

    public Resource getClassPathFile(String path){
        return applicationContext.getResource("classpath:///"+path);//spring.xml
    }

}
