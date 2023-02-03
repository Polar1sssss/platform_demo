package com.hujtb.commons.mysql.interceptor;

import com.hujtb.data.page.MyPage;
import com.hujtb.data.page.Page;
import com.hujtb.data.r.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 基于springMVC的响应体处理器 - 对返回R对象统一设置分页信息
 */
@Slf4j
public class PageResponseBodyAdvice implements ResponseBodyAdvice<R> {

    /**
     * 条件判断方法，判断什么条件下触发beforeBodyWrite方法
     *
     * @param returnType
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> aClass) {
        // 拦截方法的返回类型是R则触发beforeBodyWrite方法
        return returnType.getMethod().getReturnType() == R.class;
    }

    @Override
    public R beforeBodyWrite(R r, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        // 获取ThreadLocal中的Page对象
        Page page = MyPage.getPage();
        if (page != null) return r;
        log.info("[page responseadvice] 分页返回值处理器生效！{}", page);
        r.setPage(page);
        return r;
    }
}
