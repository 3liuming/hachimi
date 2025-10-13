package com.ruoyi.hachimiCli.service;

import com.ruoyi.hachimiSys.domain.AdoptFeedback;

import java.util.List;

public interface AdoptFeedbackService_Cli {

    List<AdoptFeedback> selectAdoptFeedbackByUserId(Long userId);
    int updateAdoptFeedbackByFeedbackId(AdoptFeedback adoptFeedback);

    int deleteAdoptFeedbackByFeedbackId(Long feedbackId);
}
