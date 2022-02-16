package com.kakuiwong.controller;

import com.kakuiwong.entity.user.UserInfo;
import com.kakuiwong.feign.EchoFeign;
import com.kakuiwong.feign.EchoFormFeign;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class FeignTestController {

    @Resource
    EchoFeign echoFeign;
    @Resource
    EchoFormFeign echoFormFeign;

    @GetMapping("json")
    public String json(UserInfo userInfo) {
        return echoFeign.json(userInfo);
    }

    @GetMapping("form/error")
    public String form1(UserInfo userInfo) {
        return echoFeign.form(userInfo);
    }

    @GetMapping("form/ok")
    public String form2(UserInfo userInfo) {
        return echoFormFeign.form(userInfo);
    }
}
