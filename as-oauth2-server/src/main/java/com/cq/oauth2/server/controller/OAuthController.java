package com.cq.oauth2.server.controller;

import com.cq.commons.model.domain.ResultInfo;
import com.cq.commons.utils.ResultInfoUtil;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Oauth2控制器
 */
@RestController
@RequestMapping("oauth")
public class OAuthController {
    @Resource
    private TokenEndpoint tokenEndpoint;

    @Resource
    private HttpServletRequest request;

    @GetMapping("hello")
    public String hello () {
        return "oauth world!";
    }

    @PostMapping("token")
    public ResultInfo postAccessToken(Principal principal, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        return custom(tokenEndpoint.postAccessToken(principal, parameters).getBody());
    }

    /**
     * 重新设置令牌返回对象
     * @param accessToken token数据
     * @return 包装后的令牌返回对象
     */
    private ResultInfo custom(OAuth2AccessToken accessToken) {
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken)accessToken;
        Map<String, Object> data = new LinkedHashMap<>(token.getAdditionalInformation());
        data.put("accessToken", token.getValue());
        data.put("expireIn", token.getExpiresIn());
        data.put("scopes", token.getScope());
        if (token.getRefreshToken() != null) {
            data.put("refreshToken", token.getRefreshToken().getValue());
        }
        return ResultInfoUtil.buildSuccess(request.getServletPath(), data);
    }
}
