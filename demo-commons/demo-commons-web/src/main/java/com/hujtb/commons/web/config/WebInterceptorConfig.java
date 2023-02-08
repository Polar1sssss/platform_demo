package com.hujtb.commons.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.List;

/**
 * 拦截器注册器
 */
public class WebInterceptorConfig implements WebMvcConfigurer {

    @Autowired(required = false)
    private List<HandlerInterceptorAdapter> interceptorAdapters;

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        for (HandlerInterceptorAdapter interceptorAdapter : interceptorAdapters) {
            registry.addInterceptor(interceptorAdapter);
        }
    }
}
