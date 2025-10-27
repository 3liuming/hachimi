package com.ruoyi.hachimiCli.service.impl;

import com.ruoyi.hachimiCli.mapper.AdoptFeedbackMapper_Cli;
import com.ruoyi.hachimiCli.mapper.AdoptionApplyMapper_Cli;
import com.ruoyi.hachimiCli.service.AdoptionApplyService_Cli;
import com.ruoyi.hachimiSys.domain.AdoptionApply;
import com.ruoyi.hachimiSys.mapper.AdoptionApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptionApplyServiceImpl_Cli implements AdoptionApplyService_Cli {

    @Autowired
    AdoptionApplyMapper adoptionApplyMapper;

    @Autowired
    AdoptionApplyMapper_Cli adoptionApplyMapperCli;

    @Override
    public List<AdoptionApply> selectAdoptionApplyByUserId(Long userId){
        AdoptionApply adoptionApply = new AdoptionApply();
        adoptionApply.setUserId(userId);
        List<AdoptionApply> adoptionApplies = adoptionApplyMapper.selectAdoptionApplyList(adoptionApply);
        if(adoptionApplies != null){
            adoptionApplies.removeIf(a -> a.getHidden() != null && a.getHidden() == 1);
            return adoptionApplies;
        }
        return null;
    }

    @Override
    public int updateAdoptionApplyByApplyId(AdoptionApply adoptionApply){
        return adoptionApplyMapperCli.updateAdoptionApplyByApplyId(adoptionApply);
    }

    @Override
    public int deleteAdoptionApplyByApplyId(Long applyId){
        return adoptionApplyMapperCli.deleteByUpdateAdoptionApplyByApplyId(applyId);
    }
}
