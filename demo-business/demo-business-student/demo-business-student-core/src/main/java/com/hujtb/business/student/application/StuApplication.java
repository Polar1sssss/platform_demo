package com.hujtb.business.student.application;

import com.hujtb.commons.web.apiversion.register.EnableApiVersion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 学生服务器的启动类
 */
@SpringBootApplication
@EnableApiVersion
public class StuApplication {
    public static void main(String[] args) {
        SpringApplication.run(StuApplication.class);
    }
}
