package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

//@SpringBootApplication
//@EnableDiscoveryClient //开启服务端发现的自动配置
//@EnableCircuitBreaker //开启当服务的熔断器支持,自动配置打开了
@SpringCloudApplication //一个顶三个
@EnableFeignClients//开启Feign功能
public class ConsumerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerServiceApplication.class, args);
    }
    //配置RestTemplate对象，注入spring容器
    @Bean
    @LoadBalanced //开启当前RestTemplate对象,支持负载均衡的能力,一旦开启传统的url地址就使用不了了
    public RestTemplate createRestTemplate(){
        return new RestTemplate();
    }
}
