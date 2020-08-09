package com.itheima.controller;

import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户服务,提供查询用户信息的接口
 */
@RestController
@RequestMapping("/user")
@RefreshScope //刷新配置
public class UserController {
    @Autowired
    UserService userService;

    @Value("${test.hello}")
    private String name;
    /**
     * 根据id查询
     */
    @RequestMapping("/findById")
    public User findById(@RequestParam("id") Integer id) {

       User user=userService.findById(id);
       user.setNote("name="+name);
       return user;
    }
}