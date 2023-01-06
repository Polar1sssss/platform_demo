package com.hujtb.business.student.valid;

import com.hujtb.business.student.input.StudentInput;
import com.hujtb.commons.web.valid.CustomValid;
import com.hujtb.commons.web.valid.MyValid;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * 生日和年龄校验器
 */
@Component
public class MyValidImpl implements MyValid<StudentInput> {

    @Override
    public boolean isValid(CustomValid customValid, StudentInput value) {

        StudentInput studentInput = value;
        Date birthDay = studentInput.getBirthDay();
        Integer age = studentInput.getAge();
        if (birthDay == null || age == null) return true;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int nowYear = calendar.get(Calendar.YEAR);
        calendar.setTime(birthDay);
        int birthYear = calendar.get(Calendar.YEAR);
        if (nowYear - birthYear != age) {
            return false;
        }
        return true;
    }
}
