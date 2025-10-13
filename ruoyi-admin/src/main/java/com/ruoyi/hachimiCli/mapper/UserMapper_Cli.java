package com.ruoyi.hachimiCli.mapper;


import com.ruoyi.hachimiCli.domaindto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper_Cli {

    // 根据用户名查询用户
    UserDto selectUserByUsername(String username);

    // 登录
    UserDto selectUserForLogin(UserDto userDto);

    String selectPasswordByUserName(String username);

    // 注册
    void insertUser(UserDto user);
}
