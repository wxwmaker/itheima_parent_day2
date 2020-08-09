package com.itheima.service;

import com.itheima.pojo.User;
import org.springframework.stereotype.Component;

/**
 * 服务降级的处理类,写feign客户端中RestTemple请求异常的服务降级的处理方法
 */
@Component//需要注意：一定要注入Spring 容器
public class UserServiceImpl implements UserService {
    @Override
    public User findById(Integer id) {
        User user = new User();
        user.setId(id);
        user.setUsername("用户不存在！！！");
        return user;
    }
}
