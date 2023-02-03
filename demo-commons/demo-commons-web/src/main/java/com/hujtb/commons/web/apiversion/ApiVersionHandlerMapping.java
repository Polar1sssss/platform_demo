package com.hujtb.commons.web.apiversion;

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
        return super.getCustomTypeCondition(handlerType);
    }

    /**
     * 程中所有的controller中的方法都会作为参数传入该方法
     *
     * @param method controller中的所有方法
     * @return
     */
    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        return super.getCustomMethodCondition(method);
    }
}
