package com.cq.marketing.center;

import com.cq.feign.client.UserClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(clients = {UserClient.class})
@SpringBootApplication
public class MarketingCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(MarketingCenterApplication.class);
    }
}
