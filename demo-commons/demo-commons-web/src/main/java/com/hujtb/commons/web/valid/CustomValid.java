package com.hujtb.commons.web.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义注解
 */
@Documented
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
// 自定义校验注解必须标记的注解，方法validateBy用于指定实际执行校验过程的类
@Constraint(validatedBy = CustomValidHandler.class)
public @interface CustomValid {

    /**
     * 必须提供的方法
     */
    String message() default "校验未通过";

    /**
     * 校验的分组信息
     */
    Class<?>[] groups() default {};

    /**
     * 设置校验的负载 - 元数据
     *
     * @return
     */
    Class<? extends Payload>[] payload() default {};

    /**
     * 参数校验实际处理类
     *
     * @return
     */
    Class<? extends MyValid> handler();
}
