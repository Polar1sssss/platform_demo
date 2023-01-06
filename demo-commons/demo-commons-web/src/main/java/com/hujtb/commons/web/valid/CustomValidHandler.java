package com.hujtb.commons.web.valid;

import com.hujtb.commons.web.utils.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义校验处理器
 */
public class CustomValidHandler implements ConstraintValidator<CustomValid, Object> {

    private CustomValid customValid;

    @Override
    public void initialize(CustomValid constraintAnnotation) {
        this.customValid = constraintAnnotation;
    }

    /**
     * 核心校验方法
     *
     * @param value   object to validate
     * @param context context in which the constraint is evaluated
     * @return
     */
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        if (value != null) {
            // 获取开发者自定义的处理器类型
            Class<? extends MyValid> handlerClass = customValid.handler();
            // 获取实际处理类的实例，注意！！！要把MyValid的实现类加到容器中啊，不然获取不到Bean  @Component @Component
            MyValid myValid = ApplicationUtils.getBean(handlerClass);
            if (myValid == null) return true;
            return myValid.isValid(customValid, value);
        }
        return true;
    }
}
