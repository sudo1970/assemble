package com.cq.marketing.center.feignClient.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class OAuth2FeignRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            Enumeration<String> headerNames = request.getHeaderNames();
            if (headerNames != null) {
                while (headerNames.hasMoreElements()) {
                    String name = headerNames.nextElement();
                    log.info("name:::{}", name);
                    String values = request.getHeader(name);
                    log.info("values:::{}", values);
                    requestTemplate.header(name, values);
                }
            }
            String accessToken = request.getHeader("access_token");
            System.out.println("=================Feign Interceptor AccessToken: " + accessToken);
            requestTemplate.header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);

            Enumeration<String> paramNames = request.getParameterNames();
            if (paramNames != null) {
                Map map=new HashMap();
                while (paramNames.hasMoreElements()) {
                    String name = paramNames.nextElement();
                    String values = request.getParameter(name);
                    requestTemplate.query(name, values);
                }
            }
        }
    }
}
