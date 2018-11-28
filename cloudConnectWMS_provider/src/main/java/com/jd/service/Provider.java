package com.jd.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @program: dubbo_demo
 * @description: 启动远程服务
 * @author: by hanpeng
 * @create: 2018-11-07 09:33
 **/
public class Provider {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-publish.xml");
        System.out.println(context.getDisplayName());
        context.start();
        System.out.println("服务已经启动");
        System.in.read();
    }
}
