package com.kakuiwong;


import com.kakuiwong.feign.EchoFeign;
import feign.Feign;
import feign.Request;
import feign.Retryer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@EnableFeignClients
@SpringBootApplication
public class OrderApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplicationMain.class, args);
    }

    //全局Feign重试
    @Bean
    public Retryer feignRetryer() {
        // 最大请求次数,包括第一次maxAttempts,设为2则重试一次
        // 重试的间隔period
        // 最大重试间隔maxPeriod
        Retryer retryer = new Retryer.Default(100, 1000, 2);
        return retryer;
    }

    //手动创建Feign
    //@Bean
    //public EchoFeign queryCouponServiceApi() {
    //    /**
    //     * 创建一个新的选项实例。
    //     * 参数: connectTimeout——值。
    //     * connectTimeoutUnit -
    //     * TimeUnit为超时值。
    //     * readTimeout——值。
    //     * readTimeoutUnit -
    //     * TimeUnit为超时值。
    //     * followRedirects—如果请求应该遵循3xx重定向。
    //     */
    //    Request.Options options = new Request.Options(5, TimeUnit.SECONDS, 10, TimeUnit.SECONDS, false);

    //    // period=100 发起当前请求的时间间隔，单位毫秒
    //    // maxPeriod=1000 发起当前请求的最大时间间隔，单位毫秒
    //    // maxAttempts=5 最多请求次数，包括第一次
    //    Retryer neverRetry = new Retryer.Default(100, 1000, 5);
    //    return Feign.builder()
    //            .encoder(new JacksonEncoder())
    //            .decoder(new JacksonDecoder())
    //            .options(options)
    //            .retryer(neverRetry)
    //            .target(EchoFeign.class, "http://auth/");
    //}
}
