package com.kakuiwong.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.kakuiwong.feign.EchoFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class EchoController {

    @Resource
    EchoFeign echoFeign;

    @GetMapping("echo")
    public String echo() {
        return echoFeign.echo();
    }


    @GetMapping("echo2")
    public String echo2() {
        return echoFeign.echo2();
    }

    /**
     * fallback指定降级方法
     */
    @SentinelResource(value = "sentinel", fallback = "sentinelFallback")
    @GetMapping("sentinel")
    public String sentinel() {
        return "sentinel";
    }

    /**
     * blockHandler指定降级方法,该方法能获取参数和异常
     */
    @SentinelResource(value = "sentinel2", blockHandler = "sentinelBlockHandler")
    @GetMapping("sentinel2")
    public String sentinel2(@RequestParam(required = true) String msg) {
        return "sentinel" + msg;
    }

    public String sentinelBlockHandler(String msg, BlockException e) {
        return "sentinelFallback" + msg + e.getMessage();
    }

    public String sentinelFallback() {
        return "sentinelFallback";
    }
}
