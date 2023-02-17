package com.hujtb.commons.web.interceptor;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * feign请求参数属性配置类
 */
@ConfigurationProperties(prefix = "feign")
@Data
public class FeignParamProperties {

    private List<String> paramNames;
}
