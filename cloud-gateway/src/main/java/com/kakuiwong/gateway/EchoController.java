package com.kakuiwong.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class EchoController {

    @Value("${name:1}")
    private String name;

    @GetMapping(value = "/echo")
    public String echo() {
        return "Hello Nacos Discovery " + name;
    }
}
