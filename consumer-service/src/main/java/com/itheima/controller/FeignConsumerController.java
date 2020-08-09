package com.itheima.controller;

import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

//消费者控制层,

/**
 * 通过feign客户端发送请求
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallBackMethod") //设置全局降级
public class FeignConsumerController {
@Autowired
    private UserService userService;

    @RequestMapping("/feignconsumer/{id}")
    public User consumerSendRequest(@PathVariable("id") Integer id){
        return userService.findById(id);
    }
}
