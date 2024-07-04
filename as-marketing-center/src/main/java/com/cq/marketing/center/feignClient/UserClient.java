package com.cq.marketing.center.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "as-user", url = "http://localhost:8085") // 需要调用的服务器名称
public interface UserClient {

    @GetMapping("/user/hello")
    String hello ();
}
