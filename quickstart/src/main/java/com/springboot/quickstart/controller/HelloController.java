package com.springboot.quickstart.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*
* springboot的第一个restful请求
* @author
* 2019.3.18
* */
@RestController
public class HelloController {
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String getHello(){
        return "Hello,Spring Boot..";
    }

}
