package com.yong.redisutil;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAsync
public class RedisUtilApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisUtilApplication.class, args);
    }

}
