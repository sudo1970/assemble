package com.cq.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "as-user") // 需要调用的服务器名称
public interface UserClient {


    @GetMapping("/user/getUser")
    String getUser ();
}
