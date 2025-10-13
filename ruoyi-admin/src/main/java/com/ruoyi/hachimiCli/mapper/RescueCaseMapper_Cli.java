package com.ruoyi.hachimiCli.mapper;

import com.ruoyi.hachimiCli.domaindto.CaseAndImgDto;
import org.apache.ibatis.annotations.Case;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface RescueCaseMapper_Cli {

    List<CaseAndImgDto> selectCaseAndImgDto(Long userId);

    // 在 Mapper 中定义两个方法
    @Update("UPDATE rescue_case SET title = #{title}, content = #{content} WHERE case_id = #{caseId}")
    int updateRescueCaseByCaseId(@Param("title") String title,
                         @Param("content") String content,
                         @Param("caseId") Long caseId);

    @Update("UPDATE pet SET rescue_place = #{rescuePlace} WHERE pet_name = #{petName}")
    int updatePetRescuePlace(@Param("rescuePlace") String rescuePlace,
                             @Param("petName") String petName);

    @Update("UPDATE rescue_case SET hidden = '1' WHERE case_id = #{caseId}")
    int deleteRescueCaseByCaseId(Long caseId);

    /**
     * 查询救助案例列表
     * @return 案例列表
     */
    List<CaseAndImgDto> selectRescueCaseList(CaseAndImgDto caseAndImgDto);

    /**
     * 根据案例ID查询救助案例详情
     * @param caseId 案例ID
     * @return 案例详情
     */
    CaseAndImgDto selectRescueCaseById(@Param("caseId") Long caseId);

    /**
     * 新增救助案例
     * @param dto 案例信息
     * @return 影响行数
     */
    int insertRescueCase(@Param("dto") CaseAndImgDto dto,@Param("userId") Long userId);

    /**
     * 修改救助案例
     * @param dto 案例信息
     * @return 影响行数
     */
    int updateRescueCase(CaseAndImgDto dto);

    /**
     * 删除救助案例
     * @param caseId 案例ID
     * @return 影响行数
     */
    int deleteRescueCaseById(@Param("caseId") Long caseId);

    /**
     * 批量删除救助案例
     * @param caseIds 案例ID数组
     * @return 影响行数
     */
    int deleteRescueCaseByIds(@Param("caseIds") Long[] caseIds);

    /**
     * 更新浏览量
     * @param caseId 案例ID
     * @return 影响行数
     */
    int incrementViewCount(@Param("caseId") Long caseId);
}
