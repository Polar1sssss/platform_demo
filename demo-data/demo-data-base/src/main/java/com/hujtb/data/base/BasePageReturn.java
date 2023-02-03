package com.hujtb.data.base;

import com.hujtb.data.page.Page;
import lombok.Data;

import java.io.Serializable;

/**
 * 基础分页信息返回对象
 */
@Data
public class BasePageReturn implements Serializable {

    private Page page;
}
