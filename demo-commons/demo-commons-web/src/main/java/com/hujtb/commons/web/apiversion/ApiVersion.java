package com.hujtb.commons.web.apiversion;

import java.lang.annotation.*;

/**
 * 标注接口方法的版本信息
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiVersion {

    // 版本信息
    double value();
}
