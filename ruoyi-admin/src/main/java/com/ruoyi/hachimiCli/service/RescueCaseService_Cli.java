package com.ruoyi.hachimiCli.service;

import com.ruoyi.hachimiCli.domaindto.CaseAndImgDto;

import java.util.List;

public interface RescueCaseService_Cli {

    List<CaseAndImgDto> selectRescueCaseByUserId(Long userId);

    int updateRescueCaseAndRescuePlaceByCaseId(CaseAndImgDto caseAndImgDto);

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
    CaseAndImgDto selectRescueCaseById(Long caseId);

    /**
     * 新增救助案例
     * @param dto 案例信息
     * @return 结果
     */
    int insertRescueCase(CaseAndImgDto dto,Long userId);

    /**
     * 修改救助案例
     * @param dto 案例信息
     * @return 结果
     */
    int updateRescueCase(CaseAndImgDto dto);

    /**
     * 删除救助案例
     * @param caseId 案例ID
     * @return 结果
     */
    int deleteRescueCaseById(Long caseId);

    /**
     * 批量删除救助案例
     * @param caseIds 案例ID数组
     * @return 结果
     */
    int deleteRescueCaseByIds(Long[] caseIds);

    /**
     * 增加浏览量
     * @param caseId 案例ID
     * @return 结果
     */
    int incrementViewCount(Long caseId);
}
