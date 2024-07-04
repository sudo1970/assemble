package com.cq.scheduler.server.config;

import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class BloomFilterConfig {
    private static final String BLOOM_NAME = "myBloomFilter"; // 布隆过滤器名称

    @Resource
    public RedissonClient redissonClient;

    @Bean
    public RBloomFilter initBloomFilter(){
        //指定布隆过滤器的名称
        return redissonClient.getBloomFilter(BLOOM_NAME);
    }
}
