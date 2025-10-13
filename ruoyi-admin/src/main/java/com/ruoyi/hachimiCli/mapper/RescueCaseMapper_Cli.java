package com.ruoyi.hachimiCli.mapper;

import com.ruoyi.hachimiCli.domaindto.CaseAndImgDto;
import org.apache.ibatis.annotations.Case;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface RescueCaseMapper_Cli {

    List<CaseAndImgDto> selectCaseAndImgDto(Long userId);

    // 在 Mapper 中定义两个方法
    @Update("UPDATE rescue_case SET title = #{title}, content = #{content} WHERE case_id = #{caseId}")
    int updateRescueCase(@Param("title") String title,
                         @Param("content") String content,
                         @Param("caseId") Long caseId);

    @Update("UPDATE pet SET rescue_place = #{rescuePlace} WHERE pet_name = #{petName}")
    int updatePetRescuePlace(@Param("rescuePlace") String rescuePlace,
                             @Param("petName") String petName);

    @Update("UPDATE rescue_case SET hidden = '1' WHERE case_id = #{caseId}")
    int deleteRescueCaseByCaseId(Long caseId);
}
