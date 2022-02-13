package com.kakuiwong.common.config;

import com.kakuiwong.entity.common.Rest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Rest exception(Exception ex) {
        return Rest.error(ex.getMessage());
    }
}
