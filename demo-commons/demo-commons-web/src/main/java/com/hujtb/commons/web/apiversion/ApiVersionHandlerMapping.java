package com.hujtb.commons.web.apiversion;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * 支持接口多版本的处理器映射器，对原有的RequestMappingHandlerMapping的拓展
 */
public class ApiVersionHandlerMapping extends RequestMappingHandlerMapping {

    /**
     * spring容器初始化时触发
     * 工程中所有的controller都会作为参数传入该方法
     * 根据当前的controller类信息返回一个开发者自定义的RequestCondition【请求条件】
     *
     * @param handlerType controller的Class对象
     * @return
     */
    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {

        // 获取类上的ApiVersion注解
        ApiVersion apiVersion = AnnotationUtils.getAnnotation(handlerType, ApiVersion.class);
        // 将注解上的值设置给请求条件对象
        return new ApiVersionRequestCondition(apiVersion == null ? apiVersion.value() : 1.0);
    }

    /**
     * 程中所有的controller中的方法都会作为参数传入该方法
     *
     * @param method controller中的所有方法
     * @return
     */
    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {

        // 获取方法上的注解
        ApiVersion apiVersion = AnnotationUtils.getAnnotation(method, ApiVersion.class);
        // 如果方法上的注解为空，尝试获取类上注解
        if (apiVersion == null) {
            apiVersion = AnnotationUtils.getAnnotation(method.getDeclaringClass(), ApiVersion.class);
        }
        return new ApiVersionRequestCondition(apiVersion == null ? apiVersion.value() : 1.0);
    }
}
