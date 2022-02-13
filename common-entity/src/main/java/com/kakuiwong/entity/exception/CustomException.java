package com.kakuiwong.entity.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int errCode = 500;

    public CustomException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public CustomException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public CustomException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.errCode = code;
    }

    public CustomException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.errCode = code;
    }
}
