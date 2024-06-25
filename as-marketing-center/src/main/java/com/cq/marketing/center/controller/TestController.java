package com.cq.marketing.center.controller;


import com.cq.feign.client.UserClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("marketingCenter")
public class TestController {

    @Resource
    private UserClient userClient;

    @GetMapping("hello")
    public String hello () {
        return "hello world!";
    }

    @GetMapping("test")
    public String test () {
        return userClient.getUser();
    }
}
