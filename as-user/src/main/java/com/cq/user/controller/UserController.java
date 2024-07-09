package com.cq.user.controller;


import com.cq.commons.dto.AccountManagerDTO;
import com.cq.commons.model.domain.ResultInfo;
import com.cq.user.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = "用户相关接口")
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private HttpServletRequest request;

    /**
     * 登录
     *
     * @param account 账号
     * @param password 密码
     * @return
     */
    @GetMapping("signin")
    public ResultInfo signIn(String account, String password) {
        return userService.signIn(account, password, request.getServletPath());
    }
    /**
     * 注册
     *
     * @param dinersDTO
     * @return
     */
    @PostMapping("register")
    public ResultInfo register(@RequestBody AccountManagerDTO dinersDTO) {
        return userService.register(dinersDTO, request.getServletPath());
    }
    @GetMapping("hello")
    public String hello() {
        return "hello user!";
    }
}
