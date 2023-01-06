package com.hujtb.business.student.input;

import com.hujtb.business.student.valid.MyValidImpl;
import com.hujtb.commons.web.valid.CustomValid;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 学生实体入参对象
 */
@Data
@CustomValid(message = "年龄和生日不符", handler = MyValidImpl.class)
public class StudentInput implements Serializable {

    @NotBlank(message = "学生姓名不能为空！")
    private String name;
    @Min(value = 10, message = "年龄不能小于10岁！")
    @Max(value = 20, message = "年龄不能大于20岁！")
    @NotNull(message = "年龄不能为空！")
    private Integer age;
    @Email
    @NotBlank(message = "邮箱不能为空！")
    private String email;
    @NotNull(message = "生日不能为空！")
    @Past(message = "生日范围不合规！")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDay;
    @NotNull(message = "性别不能为空！")
    private Integer sex;
}
