package com.hujtb.business.student.application;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 学生服务器的启动类
 */
@SpringBootApplication
public class StuApplication {

    public static void main(String[] args) {
        SpringApplication.run(StuApplication.class);
    }
}
