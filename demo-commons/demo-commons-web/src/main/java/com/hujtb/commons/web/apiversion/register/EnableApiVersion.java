package com.hujtb.commons.web.apiversion.register;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 控制多版本功能是否开启
 * 在启动类上标注了这个注解，就会把ApiVersionMappingRegister这个类交给spring容器管理
 * 等价于在BaseConfiguration中配置Bean
 *
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ApiVersionMappingRegister.class)
public @interface EnableApiVersion {
}
