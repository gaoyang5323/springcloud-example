package com.kakuiwong.feign;

import com.kakuiwong.entity.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @FeignClient fallback指定降级处理类, 该类必须是注册到spring中的bean
 */
@FeignClient(value = "user", fallback = EchoFeign.EchoFeignFallback.class)
public interface EchoFeign {
    @GetMapping("/echo")
    String echo();

    @GetMapping("/echo2")
    String echoTimeout();

    /**
     * 默认json内容类型提交参数
     * @param userInfo
     * @return
     */
    @PostMapping("json")
    String json(UserInfo userInfo);

    /**
     * 使用form内容类型提交会失败,需要单独配置编码类型
     * @param userInfo
     * @return
     */
    @PostMapping(value = "form", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    String form(UserInfo userInfo);

    @Component
    class EchoFeignFallback implements EchoFeign {
        @Override
        public String echo() {
            return "echo fallback";
        }

        @Override
        public String echoTimeout() {
            return "echo2 fallback";
        }

        @Override
        public String json(UserInfo userInfo) {
            return "json fallback";
        }

        @Override
        public String form(UserInfo userInfo) {
            return "form fallback";
        }
    }
}
