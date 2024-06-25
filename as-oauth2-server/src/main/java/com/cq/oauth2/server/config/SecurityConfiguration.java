package com.cq.oauth2.server.config;

import cn.hutool.crypto.digest.DigestUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    // 注入Redis连接工厂
    @Resource
    public RedisConnectionFactory redisConnectionFactory;

    // 初始化RedisTokenStore用于将token存储至redis
    @Bean
    public RedisTokenStore redisTokenStore() {
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setPrefix("TOKEN:");
        return redisTokenStore;
    }

    // 初始化密码编辑器，用MD5加密密码
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            /**
             * 加密
             * @param charSequence 原始密码
             * @return 加密后的密码
             */
            @Override
            public String encode(CharSequence charSequence) {
                return DigestUtil.md5Hex(charSequence.toString());
            }

            /**
             * 校验密码
             * @param charSequence 原始密码
             * @param encodedPassword 加密密码
             * @return
             */
            @Override
            public boolean matches(CharSequence charSequence, String encodedPassword) {
                return DigestUtil.md5Hex(charSequence.toString()).equals(encodedPassword);
            }
        };
    }

    // 初始化认证管理对象
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // 放行和认证规则
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                // 放行的请求
                .antMatchers("/oauth/**", "/actuator/**").permitAll()
//                .antMatchers("/actuator/**").permitAll()
                .and()
                .authorizeRequests()
                // 其他的请求必须认证才能访问
                .anyRequest().authenticated();
    }
}
