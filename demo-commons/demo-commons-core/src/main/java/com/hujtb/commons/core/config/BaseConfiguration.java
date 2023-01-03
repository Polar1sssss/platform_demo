package com.hujtb.commons.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 核心commons基础配置类
 */
@Configuration
@ComponentScan(basePackages = {"com.hujtb.business"})
public class BaseConfiguration {
}
