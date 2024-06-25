package com.cq.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients // 开启openfeign的使用
@SpringBootApplication
public class FeignApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(FeignApiApplication.class);
    }
}
