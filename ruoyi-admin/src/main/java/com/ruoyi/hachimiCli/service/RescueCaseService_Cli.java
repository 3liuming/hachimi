package com.ruoyi.hachimiCli.service;

import com.ruoyi.hachimiCli.domaindto.CaseAndImgDto;

import java.util.List;

public interface RescueCaseService_Cli {

    List<CaseAndImgDto> selectRescueCaseByUserId(Long userId);

    int updateRescueCaseAndRescuePlaceByCaseId(CaseAndImgDto caseAndImgDto);

    int deleteRescueCaseByCaseId(Long caseId);
}
