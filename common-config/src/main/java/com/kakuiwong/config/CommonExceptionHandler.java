package com.kakuiwong.config;

import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.kakuiwong.entity.common.Rest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.UndeclaredThrowableException;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Rest exception(Exception e) {
        if(e instanceof UndeclaredThrowableException){
            Throwable undeclaredThrowable = ((UndeclaredThrowableException) e).getUndeclaredThrowable();
            String msg = null;
            if (undeclaredThrowable instanceof FlowException) {
                msg = "接口限流";
            } else if (undeclaredThrowable instanceof DegradeException) {
                msg = "接口熔断";
            } else if (undeclaredThrowable instanceof ParamFlowException) {
                msg = "热点参数限流";
            } else if (undeclaredThrowable instanceof SystemBlockException) {
                msg = "系统规则限流(负载等)";
            } else if (undeclaredThrowable instanceof AuthorityException) {
                msg = "授权规则不通过";
            }
            if (msg != null) {
                return Rest.error(msg);
            }
        }
        return Rest.error(e.getMessage());
    }
}
