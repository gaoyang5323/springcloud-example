package com.kakuiwong.controller;

import com.kakuiwong.entity.user.UserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class EchoController {

    @GetMapping("echo")
    public String echo() {
        System.out.println("echo retry");
        return "this is auth1";
    }


    @GetMapping("echo2")
    public String echo2() throws InterruptedException {
        System.out.println("echo2");
        Thread.sleep(20000);
        return "this is auth2";
    }

    @PostMapping("form")
    public String form(UserInfo userInfo) {
        return "form" + userInfo.getName();
    }


    @PostMapping("json")
    public String json(@RequestBody UserInfo userInfo) {
        return "json" + userInfo.getName();
    }
}
