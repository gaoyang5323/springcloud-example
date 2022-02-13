package com.kakuiwong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoController {

    @GetMapping("echo")
    public String echo(){
        return "this is auth1";
    }


    @GetMapping("echo2")
    public String echo2(){
        return "this is auth2";
    }
}
