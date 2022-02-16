package com.kakuiwong.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EchoCircuitbreakerTestController {

    @GetMapping("/circuitbreaker/echo")
    public String echo() {
        return "circuitbreaker echo";
    }

}
