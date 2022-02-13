package com.kakuiwong.entity.common;

import lombok.Data;

@Data
public class Rest<T> {
    private T data;
    private Integer errCode;
    private String msg;

    public static Rest ok() {
        return new Rest();
    }

    public static <T> Rest<T> ok(T data) {
        Rest rest = new Rest();
        rest.setData(data);
        return rest;
    }

    public static Rest error() {
        return error(500, "未知异常，请联系管理员");
    }

    public static Rest error(String msg) {
        return error(500, msg);
    }

    public static Rest error(int code, String msg) {
        Rest r = new Rest();
        r.msg = msg;
        r.errCode = code;
        return r;
    }

    public Rest() {
        this.msg = "success";
        this.errCode = 0;
    }
}
