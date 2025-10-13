package com.ruoyi.hachimiCli.service.impl;

import com.ruoyi.hachimiCli.domaindto.CaseAndImgDto;
import com.ruoyi.hachimiCli.mapper.RescueCaseMapper_Cli;
import com.ruoyi.hachimiCli.service.RescueCaseService_Cli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class RescueCaseServiceImpl_Cli implements RescueCaseService_Cli {

    @Autowired
    RescueCaseMapper_Cli rescueCaseMapperCli;

    @Override
    public List<CaseAndImgDto> selectRescueCaseByUserId(Long userId){
        List<CaseAndImgDto> caseAndImgDtos = rescueCaseMapperCli.selectCaseAndImgDto(userId);
        if(caseAndImgDtos != null){
            return caseAndImgDtos;
        }
        return null;
    }

    @Override
    public int updateRescueCaseAndRescuePlaceByCaseId(CaseAndImgDto caseAndImgDto){

        int result1 = rescueCaseMapperCli.updateRescueCaseByCaseId(caseAndImgDto.getTitle(),caseAndImgDto.getContent(),caseAndImgDto.getCaseId());
        int result2 = rescueCaseMapperCli.updatePetRescuePlace(caseAndImgDto.getRescuePlace(),caseAndImgDto.getPetName());
        if (result1 == 1 && result2 == 1){
            return 1;
        }
        return 0;
    }

    @Override
    public int deleteRescueCaseByCaseId(Long caseId){
        return rescueCaseMapperCli.deleteRescueCaseByCaseId(caseId);
    }

    /**
     * 查询救助案例列表
     * @return 案例列表
     */
    @Override
    public List<CaseAndImgDto> selectRescueCaseList(CaseAndImgDto caseAndImgDto) {
        return rescueCaseMapperCli.selectRescueCaseList(caseAndImgDto);
    }

    /**
     * 根据案例ID查询救助案例详情
     * @param caseId 案例ID
     * @return 案例详情
     */
    @Override
    public CaseAndImgDto selectRescueCaseById(Long caseId) {
        // 查询详情时增加浏览量
        rescueCaseMapperCli.incrementViewCount(caseId);
        return rescueCaseMapperCli.selectRescueCaseById(caseId);
    }

    /**
     * 新增救助案例
     * @param dto 案例信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertRescueCase(CaseAndImgDto dto,Long userId) {
        // 设置默认值
        if (dto.getPublishTime() == null) {
            dto.setPublishTime(new Date());
        }
        if (dto.getViewCount() == null) {
            dto.setViewCount(0L);
        }

        // 调用 Mapper，传入两个参数
        return rescueCaseMapperCli.insertRescueCase(dto, userId);
    }

    /**
     * 修改救助案例
     * @param dto 案例信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateRescueCase(CaseAndImgDto dto) {
        return rescueCaseMapperCli.updateRescueCase(dto);
    }

    /**
     * 删除救助案例
     * @param caseId 案例ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRescueCaseById(Long caseId) {
        return rescueCaseMapperCli.deleteRescueCaseById(caseId);
    }

    /**
     * 批量删除救助案例
     * @param caseIds 案例ID数组
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRescueCaseByIds(Long[] caseIds) {
        return rescueCaseMapperCli.deleteRescueCaseByIds(caseIds);
    }

    /**
     * 增加浏览量
     * @param caseId 案例ID
     * @return 结果
     */
    @Override
    public int incrementViewCount(Long caseId) {
        return rescueCaseMapperCli.incrementViewCount(caseId);
    }
}
