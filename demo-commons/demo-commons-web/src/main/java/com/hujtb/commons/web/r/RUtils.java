package com.hujtb.commons.web.r;

import com.hujtb.commons.web.enums.Codes;

/**
 * 快捷生成R对象的方法
 */
public class RUtils {

    /**
     * 生成成功标识的R对象
     */
    public static <T> R create(T data) {
        return new R(Codes.SUCC.getCode(), Codes.SUCC.getMsg(), data);
    }

    /**
     * 生成一个指定响应码的R对象
     */
    public static <T> R create(Codes codes, T data) {
        return new R(codes.getCode(), codes.getMsg(), data);
    }

    /**
     * 生成一个指定响应码的R对象，不携带数据
     */
    public static <T> R create(Codes codes) {
        return new R(codes.getCode(), codes.getMsg(), null);
    }
}
