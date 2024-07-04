package com.cq.scheduler.server.service;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class BloomFilterService {
    private static final int EXPECTED_INSERTIONS = 100000; // 预期插入数量
    private static final double FPR = 0.01; // 误报率

    @Resource
    private RBloomFilter bloomFilter;

    public void sysDBAndBloomFilter () {
        log.info("=======sysDBAndBloomFilter=========");
        //初始化布隆过滤器大小与容错率
        bloomFilter.tryInit(EXPECTED_INSERTIONS,FPR);
//        bloomFilter.add("111");
    }
}
