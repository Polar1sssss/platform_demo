package com.hujtb.commons.web.enums;


public enum Codes {

    SUCC(200, "成功"),
    FAIL(500, "服务器异常"),
    PARAM_ERROR(501, "参数校验异常");

    private Integer code;
    private String msg;

    Codes(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
