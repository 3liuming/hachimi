package com.ruoyi.hachimiCli.mapper;

import com.ruoyi.hachimiCli.domaindto.PersonalDto;
import com.ruoyi.hachimiCli.domaindto.PublicDto;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface PersonalMapper_Cli {

    PublicDto selectPublicDto(Long userId);

    @Results({
            @Result(property = "realName", column = "real_name"),
            @Result(property = "idCard", column = "id_card")
    })
    @Select("SELECT real_name, id_card FROM user WHERE user_id = #{userId} AND hidden = '0' LIMIT 1")
    PersonalDto selectPersonalDto(Long userId);

    int updatePublicDto(PublicDto publicDto);

    int updatePwdByRealNameAndIdCard(PersonalDto personalDto);

    int updateRealNameAndIdCardByPwd(PersonalDto personalDto);

    String selectPasswordByUserId(Long userId);

//    @Results({
//            @Result(property = "realName", column = "real_name"),
//            @Result(property = "idCard",   column = "id_card"),
//            @Result(property = "userId", column = "user_id")
//    })
//    @Select("SELECT count(*) FROM user WHERE user_id = #{userId} AND id_card = #{idCard} AND real_name = #{realName} AND hidden = '0'")
//    PersonalDto selectRealNameAndIdCard(Long userId,String idCard,String realName);
}
