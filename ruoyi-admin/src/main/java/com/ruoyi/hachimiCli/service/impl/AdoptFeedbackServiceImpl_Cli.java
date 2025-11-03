package com.ruoyi.hachimiCli.service.impl;

import com.ruoyi.hachimiCli.mapper.AdoptFeedbackMapper_Cli;
import com.ruoyi.hachimiCli.service.AdoptFeedbackService_Cli;
import com.ruoyi.hachimi_admin.domain.AdoptFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdoptFeedbackServiceImpl_Cli implements AdoptFeedbackService_Cli {

    @Autowired
    AdoptFeedbackMapper_Cli adoptFeedbackMapperCli;

    public List<AdoptFeedback> selectAdoptFeedbackByUserId(Long userId){

        List<AdoptFeedback> adoptFeedbacks = adoptFeedbackMapperCli.selectAdoptFeedbackList(userId);
        if(adoptFeedbacks != null){
            return adoptFeedbacks;
        }
        return null;
    }

    @Override
    public int updateAdoptFeedbackByFeedbackId(AdoptFeedback adoptFeedback){
        return adoptFeedbackMapperCli.updateAdoptFeedbackByFeedbackId(adoptFeedback);
    }

    @Override
    public int deleteAdoptFeedbackByFeedbackId(Long feedbackId){
        return adoptFeedbackMapperCli.deleteAdoptFeedbackByFeedbackId(feedbackId);
    }
}
