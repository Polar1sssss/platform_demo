package com.hujtb.commons.mysql.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MySQL的自动装配
 */
@Configuration
@MapperScan({"com.hujtb.data.mapper"})
@EnableTransactionManagement
public class MySQLAutoConfiguration {
}
