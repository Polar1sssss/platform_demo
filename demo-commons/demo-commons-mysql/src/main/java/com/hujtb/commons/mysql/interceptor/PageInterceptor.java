package com.hujtb.commons.mysql.interceptor;

import com.hujtb.data.page.MyPage;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 分页插件拦截器
 * 引入springmvc依赖以后需要在在module中添加tomcat
 */
@Component
public class PageInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        if (pageNum != null && pageSize != null) {
            try {
                int pNum = Integer.parseInt(pageNum);
                int pSize = Integer.parseInt(pageSize);
                // 请求中包含这两个参数，证明需要分页
                MyPage.setPage(pNum, pSize);
            } catch (Exception e) {
                throw e;
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        // 清除ThreadLocal
        MyPage.clear();
    }
}
