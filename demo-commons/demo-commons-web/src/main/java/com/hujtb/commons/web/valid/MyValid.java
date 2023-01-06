package com.hujtb.commons.web.valid;

/**
 * 架构层校验的实现接口
 */
public interface MyValid<T> {

    boolean isValid(CustomValid customValid, T value);
}
