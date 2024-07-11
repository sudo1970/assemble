package com.cq.marketing.center;

import com.cq.marketing.center.feignClient.UserClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableFeignClients(clients = {UserClient.class})
@EnableAsync
@SpringBootApplication
public class MarketingCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarketingCenterApplication.class);
    }
}
