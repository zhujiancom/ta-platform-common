package com.ta.platform.common.clientapi;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Creator: zhuji
 * Date: 5/26/2020
 * Time: 7:21 PM
 * Description: feign 请求拦截
 */
@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor(){
        return new FeignBasicAuthRequestInterceptor();
    }
}
