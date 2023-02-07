package com.hujtb.commons.web.config;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.hujtb.commons.web.exception.GlobalException;
import com.hujtb.commons.web.utils.ApplicationUtils;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.SimpleFormatter;

/**
 * Web模块的自动装配核心配置文件
 */
@Configuration
public class BaseConfiguration {

    /**
     * 相关基础、工具配置类
     */
    @Configuration
    public static class BaseAutoConfiguration {
        /**
         * 装配Application工具类
         *
         * @return
         */
        @Bean
        public ApplicationUtils getApplicationUtils() {
            return new ApplicationUtils();
        }
    }

    /**
     * 相关Web配置
     */
    public static class WebMvcConfiguration {

        @Bean
        public GlobalException getGlobalException() {
            return new GlobalException();
        }

        /**
         * 注册Web拦截器配置类
         *
         * @return
         */
        @Bean
        public WebInterceptorConfig getWebInterceptorConfig() {
            return new WebInterceptorConfig();
        }
    }

    /**
     * Nacos相关配置
     */
    @Configuration
    @EnableDiscoveryClient
    public static class NacosConfiguration {

        /**
         * 配置相关的微服务的元数据信息
         * NacosDiscoveryAutoConfiguration.class中配置的getNacosDiscoveryProperties方法上标注了@ConditionalOnMissingBean
         * 即如果容器中没有NacosDiscoveryProperties实例就会创建该类的实例
         */
        @Bean
        @Primary
        public NacosDiscoveryProperties getNacosDiscoveryProperties() {

            NacosDiscoveryProperties properties = new NacosDiscoveryProperties();
            Map<String, String> metadata = new HashMap();
            metadata.put("online-time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            properties.setMetadata(metadata);
            return properties;
        }
    }
}

