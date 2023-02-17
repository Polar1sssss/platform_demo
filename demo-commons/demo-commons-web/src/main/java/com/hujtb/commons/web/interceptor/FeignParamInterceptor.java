package com.hujtb.commons.web.interceptor;

import com.hujtb.commons.web.utils.RequestUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * feign传参拦截器，自定义给feign请求中添加相应参数
 */
public class FeignParamInterceptor implements RequestInterceptor {

    // 请求参数名的列表
    @Autowired
    private FeignParamProperties feignParamProperties;

    @Override
    public void apply(RequestTemplate requestTemplate) {

        List<String> paramNames = feignParamProperties.getParamNames();

        if (paramNames.size() == 0 || paramNames == null) return;
        // 获取feign请求需要传递的参数，参数可能存在与请求头和参数列表
        HttpServletRequest httpServletRequest = RequestUtils.getHttpServletRequest();

        for (String paramName : paramNames) {
            // 根据参数名获取请求头中的参数值
            String headerValue = httpServletRequest.getHeader(paramName);
            if (!StringUtils.isEmpty(headerValue)) {
                // 获取到参数值，将参数值放到请求头中
                requestTemplate.header(paramName, headerValue);
            }

            // 根据参数名获取参数列表中的参数值
            String paramValue = httpServletRequest.getParameter(paramName);
            if (!StringUtils.isEmpty(paramValue)) {
                requestTemplate.query(paramName, paramValue);
            }
        }
    }
}
