package com.cq.user.controller;

import com.cq.commons.model.domain.ResultInfo;
import com.cq.commons.utils.ResultInfoUtil;
import com.cq.user.service.SendVerifyCodeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class SendVerifyCodeController {
    @Resource
    private SendVerifyCodeService sendVerifyCodeService;
    @Resource
    private HttpServletRequest request;

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @GetMapping("send")
    public ResultInfo send(String phone) {
        sendVerifyCodeService.send(phone);
        return ResultInfoUtil.buildSuccess("发送成功", request.getServletPath());
    }
}
