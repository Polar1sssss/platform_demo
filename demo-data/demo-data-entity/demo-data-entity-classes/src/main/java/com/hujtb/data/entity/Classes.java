package com.hujtb.data.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.hujtb.data.base.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 班级表实体
 */
@Data
@Accessors(chain = true)
public class Classes {

    // 主键
    @TableId(type = IdType.AUTO)
    private Long id;
    // 班级名称
    private String className;
    private BaseEntity baseEntity;
}
