package com.example.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author wskg
 * @create 2022/11/3
 * @Description
 */


@SpringBootApplication
@MapperScan("com.example.server.mapper")
@EnableScheduling
@EnableSwagger2
public class YunApplication {
    public static void main(String[] args) {
        SpringApplication.run(YunApplication.class, args);
    }
}
