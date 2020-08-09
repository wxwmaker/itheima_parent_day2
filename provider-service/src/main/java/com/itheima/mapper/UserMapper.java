package com.itheima.mapper;

import com.itheima.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {
    //根据id查询用户信息
    @Select("select * from tb_user where id=#{id} ;")
    User findById(Integer id);
}
