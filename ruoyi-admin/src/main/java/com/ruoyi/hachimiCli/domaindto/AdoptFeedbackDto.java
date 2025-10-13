package com.ruoyi.hachimiCli.domaindto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.util.Date;

public class AdoptFeedbackDto {

    /** 反馈ID */
    @Excel(name = "反馈ID")
    private Long feedbackId;

    /** 关联领养申请ID */
    @Excel(name = "关联领养申请ID")
    private Long applyId;

    /** 反馈内容（如：“宠物很健康，适应良好”） */
    @Excel(name = "反馈内容", readConverterExp = "如=：“宠物很健康，适应良好”")
    private String content;

    /** 反馈图片URL（可选） */
    @Excel(name = "反馈图片URL", readConverterExp = "可=选")
    private String feedbackImage;

    /** 反馈发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "反馈发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date feedbackTime;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long likeCount;
}
