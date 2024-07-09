package com.cq.scheduler.server.service;

import com.cq.scheduler.server.mapper.ProductMapper;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RBloomFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class BloomFilterService {
    private static final int EXPECTED_INSERTIONS = 100000; // 预期插入数量
    private static final double FPR = 0.01; // 误报率

    @Resource
    private RBloomFilter bloomFilter;

    @Resource
    private ProductMapper productMapper;

    public void sysDBAndBloomFilter () {
        log.info("=======sysDBAndBloomFilter=========");
        //初始化布隆过滤器大小与容错率
        bloomFilter.tryInit(EXPECTED_INSERTIONS,FPR);
        List<String> list = productMapper.selectAllProductId();
        log.info("list:::{}", list.size());
        log.info("bloomFilter.getSize before:::{}", bloomFilter.getSize());
        bloomFilter.add(list);
        log.info("bloomFilter.getSize after:::{}", bloomFilter.getSize());
    }
}
