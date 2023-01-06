package com.hujtb.commons.web.exception;

import com.hujtb.commons.web.enums.Codes;
import com.hujtb.commons.web.r.R;
import com.hujtb.commons.web.r.RUtils;
import com.hujtb.commons.web.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局异常类
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {

    /**
     * 参数校验异常处理
     *
     * @param bindException
     * @return
     */
    @ExceptionHandler(BindException.class)
    public R bindExceptionHandler(BindException bindException) {

        Set<String> errors = bindException.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toSet());
        // 返回错误信息
        return RUtils.create(Codes.PARAM_ERROR, errors);
    }

    /**
     * 基于@RequestBody接收参数时的参数校验异常
     *
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R methodArgumentNotValidException(MethodArgumentNotValidException exception) {

        Set<String> errors = exception.getBindingResult().getAllErrors()
                .stream()
                .map(objectError -> objectError.getDefaultMessage())
                .collect(Collectors.toSet());
        // 返回错误信息
        return RUtils.create(Codes.PARAM_ERROR, errors);
    }

    /**
     * 基于形参列表进行校验的参数校验异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public R constraintViolationException(ConstraintViolationException exception) {

        Set<String> errors = exception.getConstraintViolations()
                .stream()
                .map(objectError -> objectError.getMessage())
                .collect(Collectors.toSet());
        // 返回错误信息
        return RUtils.create(Codes.PARAM_ERROR, errors);
    }

    /**
     * 统一异常处理方法
     *
     * @param t
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public R exception(Throwable t) {

        try {
            // 获取当前请求的url
            HttpServletRequest httpServletRequest = RequestUtils.getHttpServletRequest();
            if (httpServletRequest != null) {
                String uri = httpServletRequest.getRequestURI().toString();
                log.error("[Global-Exception] - 捕获到全局异常~请求路径为：" + uri, t);
            }
        } catch (Exception e) {
            log.error("[Global-Exception] - 捕获到全局异常~", t);
        }
        return RUtils.create(Codes.FAIL);
    }
}
