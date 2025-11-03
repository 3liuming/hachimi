package com.ruoyi.hachimiCli.service;

import com.ruoyi.hachimi_admin.domain.AdoptionApply;

import java.util.List;

public interface AdoptionApplyService_Cli {

    List<AdoptionApply> selectAdoptionApplyByUserId(Long userId);

    int updateAdoptionApplyByApplyId(AdoptionApply adoptionApply);

    int deleteAdoptionApplyByApplyId(Long applyId);
}
