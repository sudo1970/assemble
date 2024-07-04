package com.cq.marketing.center.controller;


import com.cq.marketing.center.feignClient.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
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
        return userClient.hello();
    }
}
