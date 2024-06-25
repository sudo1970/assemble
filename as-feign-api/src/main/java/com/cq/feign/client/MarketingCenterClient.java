package com.cq.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "as-marketing-center") // 需要调用的服务器名称
public interface MarketingCenterClient {

    @GetMapping("hello")
    String hello ();
}
