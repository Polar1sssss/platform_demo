package com.hujtb.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
    // 班级的外键
    private Long cId;
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
    // 班级信息，在数据库中是没有这个字段的
    @TableField(exist = false)
    private Classes cls;
    private BaseEntity baseEntity;
}
