package com.cq.scheduler.server;

import com.cq.scheduler.server.service.BloomFilterService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

@MapperScan("com.cq.scheduler.server.mapper")
@EnableScheduling
@SpringBootApplication
public class SchedulerServerApplication {
    @Resource
    private BloomFilterService bloomFilterService;

    public static void main(String[] args) {
        SpringApplication.run(SchedulerServerApplication.class, args);
    }

    @Scheduled(fixedRate = 3000)
    public void performTask() {
        // 定时任务的逻辑
        System.out.println("Task executed!");
        bloomFilterService.sysDBAndBloomFilter();
    }
}
