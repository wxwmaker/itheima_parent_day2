package com.itheima.controller;

import com.itheima.pojo.User;
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


@RestController
@RequestMapping("/consumer")
@DefaultProperties(defaultFallback = "defaultFallBackMethod") //设置全局降级
public class ConsumerController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("{id}")
    public User queryById(@PathVariable Long id){
        //1、通过注册中心客户端对象DiscoveryClient，获取Eureka中注册的user-service实例列表
        List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("user-service");
        //2、获取user-service服务实例对象
        ServiceInstance userServiceInstance = serviceInstanceList.get(0);
       String host=userServiceInstance.getHost();
       int post=userServiceInstance.getPort();
        Map<String,String> metadata=userServiceInstance.getMetadata();
        String url="http://"+host+":"+post+"/user/findById?id="+id;
        return restTemplate.getForObject(url,User.class);
    }


    @GetMapping("/ribbon/{id}")
//    @HystrixCommand(fallbackMethod = "ribbonFallback")
    @HystrixCommand
    public User ribbon(@PathVariable("id") Long id){
//负载均衡访问服务的url地址,注意host 和port 变为服务名称,传统的地址随即失效
        String url="http://user-service/user/findById?id="+id;
        return restTemplate.getForObject(url,User.class);
    }

    /**
     *     编写服务降级方法
     *     与被降级方法参数和返回值必须保持一致
     *     方法名称一定不能保持一致
     */
    public User ribbonFallback(Long id){
        User user=new User();
        user.setUsername("您好,非常抱歉您访问的用户信息不存在!!");
        return user;
    }

    //全局的默认的熔断方法必须参数为空
    public User defaultFallBackMethod(){
        User user=new User();
        user.setUsername("全局默认的服务降级方法,非常抱歉您访问的用户信息不存在!!");
        return user;
    }
}
