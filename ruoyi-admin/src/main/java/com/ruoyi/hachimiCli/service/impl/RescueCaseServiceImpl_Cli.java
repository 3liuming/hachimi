package com.ruoyi.hachimiCli.service.impl;

import com.ruoyi.hachimiCli.domaindto.CaseAndImgDto;
import com.ruoyi.hachimiCli.mapper.RescueCaseMapper_Cli;
import com.ruoyi.hachimiCli.service.RescueCaseService_Cli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        int result1 = rescueCaseMapperCli.updateRescueCase(caseAndImgDto.getTitle(),caseAndImgDto.getContent(),caseAndImgDto.getCaseId());
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
}
