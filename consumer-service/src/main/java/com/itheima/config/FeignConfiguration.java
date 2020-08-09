package com.itheima.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign客户端的接口的配置类
 */
@Configuration
public class FeignConfiguration {
    /**
     * 注入日志级别的配置到spring的容器中
     * @return
     */
    @Bean
    public Logger.Level feignLoggerLevel(){
        //记录所有请求和响应的明细，包括头信息，请求体，元数据
        return Logger.Level.FULL;
    }
}
