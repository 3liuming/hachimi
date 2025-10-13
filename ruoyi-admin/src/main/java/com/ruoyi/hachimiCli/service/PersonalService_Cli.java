package com.ruoyi.hachimiCli.service;

import com.ruoyi.hachimiCli.domaindto.PersonalDto;
import com.ruoyi.hachimiCli.domaindto.PublicDto;

public interface PersonalService_Cli {

    // 查询公开信息
    PublicDto getPublicInfo(Long userId);

    // 查询隐私信息
    PersonalDto getPersonalInfo(Long userId);
    // 更新公开信息
    int updatePublicInfo(Long userId, PublicDto publicDto);

    // 更新隐私信息
    int updatePersonalInfo(Long userId, PersonalDto personalDto);
}
