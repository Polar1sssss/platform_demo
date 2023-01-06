package com.hujtb.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hujtb.data.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 学生表实体
 */
@Data
@Accessors(chain = true)
public class Student {

    // 主键
    @TableId(type = IdType.AUTO)
    private Long id;
    // 姓名
    private String name;
    // 年龄
    private Integer age;
    // 邮箱
    private String email;
    // 生日
    private Date birthday;
    // 性别
    private Integer sex;

    private BaseEntity baseEntity;
}
