package com.ruoyi.hachimiCli.service.impl;

import com.ruoyi.hachimiCli.domaindto.PersonalDto;
import com.ruoyi.hachimiCli.domaindto.PublicDto;
import com.ruoyi.hachimiCli.mapper.PersonalMapper_Cli;
import com.ruoyi.hachimiCli.service.PersonalService_Cli;
import com.ruoyi.tool.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonalServiceImpl_Cli implements PersonalService_Cli {

    @Autowired
    private PersonalMapper_Cli personalMapper;


    @Override
    public PublicDto getPublicInfo(Long userId) {
        return personalMapper.selectPublicDto(userId);
    }

    @Override
    public PersonalDto getPersonalInfo(Long userId) {
        return personalMapper.selectPersonalDto(userId);
    }

    @Override
    public int updatePublicInfo(Long userId, PublicDto publicDto) {
        publicDto.setUserId(userId);
        return personalMapper.updatePublicDto(publicDto);
    }

    @Override
    public int updatePersonalInfo(Long userId, PersonalDto personalDto) {
        personalDto.setUserId(userId);
        String inputPwd = personalDto.getPassword();
        String encoded = personalMapper.selectPasswordByUserId(userId);

        if ((PasswordUtil.checkPassword(inputPwd,encoded))){
            return personalMapper.updateRealNameAndIdCardByPwd(personalDto);
        }

        PersonalDto oldInfo = personalMapper.selectPersonalDto(userId);
        if(personalDto.getRealName().equals(oldInfo.getRealName()) && personalDto.getIdCard().equals(oldInfo.getIdCard())){
            String Pwd = PasswordUtil.encryptPassword(personalDto.getPassword());
            personalDto.setPassword(Pwd);
            return personalMapper.updatePwdByRealNameAndIdCard(personalDto);
        }

        return 0;
    }
}