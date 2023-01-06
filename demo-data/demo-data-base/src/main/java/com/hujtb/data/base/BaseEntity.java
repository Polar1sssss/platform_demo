package com.hujtb.data.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable {

    // 创建时间
    private Date createTime;
    // 修改时间
    private Date updateTime;
    // 状态
    private Integer status;
    // 删除标志
    private Integer delFlag;
}
