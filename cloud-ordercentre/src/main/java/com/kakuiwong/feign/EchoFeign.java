package com.kakuiwong.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @FeignClient fallback指定降级处理类,该类必须是注册到spring中的bean
 */
@FeignClient(value = "user", fallback = EchoFeign.EchoFeignFallback.class)
public interface EchoFeign {

    @GetMapping("/echo")
    String echo();

    @GetMapping("/echo2")
    String echo2();


    @Component
    class EchoFeignFallback implements EchoFeign {
        @Override
        public String echo() {
            return "echo fallback";
        }

        @Override
        public String echo2() {
            return "echo2 fallback";
        }
    }
}
