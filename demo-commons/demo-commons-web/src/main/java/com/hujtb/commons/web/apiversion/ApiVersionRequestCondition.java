package com.hujtb.commons.web.apiversion;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;

public class ApiVersionRequestCondition implements RequestCondition<ApiVersionRequestCondition> {

    // 表示当前条件的版本号
    private double apiVersion = 1.0;

    private static final String VERSION_NAME = "api-version";

    public double getApiVersion() {
        return apiVersion;
    }

    public ApiVersionRequestCondition(double apiVersion) {
        this.apiVersion = apiVersion;
    }

    /**
     * 当客户端发送请求的时候触发这三个方法
     * 当类上条件和方法上条件同时存在时，直接使用method的请求条件
     *
     * @param method controller中某个方法
     * @return
     */
    @Override
    public ApiVersionRequestCondition combine(ApiVersionRequestCondition method) {

        // this代表controller的请求条件对象
        // method代表controller中某个方法的请求条件对象
        return method;
    }

    /**
     * 根据请求，判断当前RequestCondition对象是否符合匹配条件。如果符合，返回当前条件对象，不符合返回空
     *
     * @param request
     * @return
     */
    @Override
    public ApiVersionRequestCondition getMatchingCondition(HttpServletRequest request) {

        double apiVersionDouble = 1.0;

        // 获取请求头中的版本信息，如果没有获取到，从参数中获取。如果都没有获取到，则使用默认值
        String reqVersion = request.getHeader(VERSION_NAME);
        if (StringUtils.isEmpty(reqVersion)) {
            reqVersion = request.getParameter(VERSION_NAME);
        }
        if (!StringUtils.isEmpty(reqVersion)) {
            try {
                apiVersionDouble = Math.max(Double.parseDouble(reqVersion), 1.0);
            } catch (Exception e) {
                apiVersionDouble = 1.0;
            }

        }

        // 将从请求中获取到的版本号与RequestCondition对象中的版本号对比
        // 采用<=的方式比较，支持服务端接口的向上兼容。例如：2.0版本的接口，可以支持2.0+的请求
        if (this.getApiVersion() == apiVersionDouble) {
            return this;
        }
        return null;
    }

    /**
     * 排序，为了选择最优的条件匹配对象
     *
     * @param other
     * @param httpServletRequest
     * @return
     */
    @Override
    public int compareTo(ApiVersionRequestCondition other, HttpServletRequest httpServletRequest) {
        // 使用降序方式排序，例如同时存在1.0和2.0接口，请求为3.0，第一选择为2.0
        return Double.compare(other.getApiVersion(), this.getApiVersion());
    }
}
