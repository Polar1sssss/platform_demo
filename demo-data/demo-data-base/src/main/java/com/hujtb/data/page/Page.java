package com.hujtb.data.page;

import lombok.Data;

import java.io.Serializable;

@Data
public class Page implements Serializable {

    private Integer pageNum; // 当前页
    private Integer pageSize; // 每页条数
    private Integer pageCount; // 总条数
    private Integer pageTotal; // 总页数
}
