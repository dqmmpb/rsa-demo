package com.alphabeta.common;

public enum ErrorCode {

    // 系统核心异常（4位异常代码，前2位为模块代码，后2位为模块内部代码）

    // 系统异常
    ERROR_SYS_EXCEPTION(1001, "系统异常错误"),
    ERROR_INVALID_REQUEST(1002, "非法请求"),

    // RSA
    ERROR_SIGNATURE_NOT_PASS(1003, "验签失败"),;

    private int code;
    private String name;

    ErrorCode(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public String getCodeString() {
        return "" + code;
    }
}
