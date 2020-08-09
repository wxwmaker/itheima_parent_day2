package com.itheima.service;

import com.itheima.config.FeignConfiguration;
import com.itheima.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-service",fallback = UserServiceImpl.class,configuration = FeignConfiguration.class)//指定feign调用的服务
public interface UserService {

    @RequestMapping("/user/findById")
    User findById(@RequestParam("id") Integer id); //问号传参 逆向生成请求地址
}
