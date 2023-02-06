package com.hujtb.commons.web.config;

import com.hujtb.commons.web.exception.GlobalException;
import com.hujtb.commons.web.utils.ApplicationUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Web模块的自动装配核心配置文件
 */
@Configuration
public class BaseConfiguration {

    @Bean
    public GlobalException getGlobalException() {
        return new GlobalException();
    }

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
}

