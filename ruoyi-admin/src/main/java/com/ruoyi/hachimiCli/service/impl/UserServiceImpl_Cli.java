package com.ruoyi.hachimiCli.service.impl;

import com.ruoyi.hachimiCli.domaindto.UserDto;
import com.ruoyi.hachimiCli.mapper.UserMapper_Cli;
import com.ruoyi.hachimiCli.service.UserService_Cli;
import com.ruoyi.tool.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl_Cli implements UserService_Cli {

    @Autowired
    private UserMapper_Cli userMapper;

    // 登录功能
    @Override
    public UserDto login(UserDto userDto) {
        try {
            String inputPwd = userDto.getPassword();
            String encoded = userMapper.selectPasswordByUserName(userDto.getUsername());
            if(PasswordUtil.checkPassword(inputPwd,encoded)){
            return userMapper.selectUserForLogin(userDto);
            }
            return null;
        } catch (DataAccessException e) {
            throw new RuntimeException("数据库查询失败", e);
        }
    }

    // 注册功能
    @Override
    public UserDto register(UserDto user) {
        // 检查用户名是否重复
        UserDto existingUser = userMapper.selectUserByUsername(user.getUsername());
        if (existingUser != null) {
            return null;  // 用户名已存在，注册失败
        }
        // 注册新用户
        try {
            String inputPwd = PasswordUtil.encryptPassword(user.getPassword());
            user.setPassword(inputPwd);//密码加密
            userMapper.insertUser(user);
            return user;  // 返回注册的用户
        } catch (DataAccessException e) {
            throw new RuntimeException("数据库操作失败", e);
        }
    }

    // 检查用户名是否存在
    @Override
    public boolean isUsernameTaken(String username) {
        UserDto existingUser = userMapper.selectUserByUsername(username);
        return existingUser != null;
    }
}
