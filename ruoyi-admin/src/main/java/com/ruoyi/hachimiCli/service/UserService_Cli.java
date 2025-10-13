package com.ruoyi.hachimiCli.service;

import com.ruoyi.hachimiCli.domaindto.UserDto;

public interface UserService_Cli {

    public UserDto login(UserDto userDto);

    public UserDto register(UserDto user);

    public boolean isUsernameTaken(String username);
}
