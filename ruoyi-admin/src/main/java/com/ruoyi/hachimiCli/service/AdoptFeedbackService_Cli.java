package com.ruoyi.hachimiCli.service;

import com.ruoyi.hachimi_admin.domain.AdoptFeedback;

import java.util.List;

public interface AdoptFeedbackService_Cli {

    List<AdoptFeedback> selectAdoptFeedbackByUserId(Long userId);
    int updateAdoptFeedbackByFeedbackId(AdoptFeedback adoptFeedback);

    int deleteAdoptFeedbackByFeedbackId(Long feedbackId);
}
