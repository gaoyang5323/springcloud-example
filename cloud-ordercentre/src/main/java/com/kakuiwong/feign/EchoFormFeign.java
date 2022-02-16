package com.kakuiwong.feign;

import com.kakuiwong.entity.user.UserInfo;
import feign.Logger;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 支持form内容类型的编码;
 * @FeignClient相同value会报错相同bean注册到spring,解决方法:
 * (1)使用contextId来区分;
 * (2)使用手动编码FeignBuilder方式
 */
@FeignClient(value = "user", contextId = "form", configuration = EchoFormFeign.FeignFormConfig.class)
public interface EchoFormFeign {

    @PostMapping(value = "form", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    String form(UserInfo userInfo);

    class FeignFormConfig {
        @Autowired
        private ObjectFactory<HttpMessageConverters> messageConverters;

        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder(new SpringEncoder(messageConverters));
        }

        @Bean
        public Logger.Level logger() {
            return Logger.Level.FULL;
        }
    }
}
