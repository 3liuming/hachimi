package com.ruoyi.hachimiSys.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 领养后反馈信息对象 adopt_feedback
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public class AdoptFeedback extends BaseEntity
{
    private static final long serialVersionUID = 1L;

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

    /** 是否隐藏：0-不隐藏；1-隐藏 */
    @Excel(name = "是否隐藏：0-不隐藏；1-隐藏")
    private Long hidden;

    public void setFeedbackId(Long feedbackId) 
    {
        this.feedbackId = feedbackId;
    }

    public Long getFeedbackId() 
    {
        return feedbackId;
    }

    public void setApplyId(Long applyId) 
    {
        this.applyId = applyId;
    }

    public Long getApplyId() 
    {
        return applyId;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setFeedbackImage(String feedbackImage) 
    {
        this.feedbackImage = feedbackImage;
    }

    public String getFeedbackImage() 
    {
        return feedbackImage;
    }

    public void setFeedbackTime(Date feedbackTime) 
    {
        this.feedbackTime = feedbackTime;
    }

    public Date getFeedbackTime() 
    {
        return feedbackTime;
    }

    public void setLikeCount(Long likeCount) 
    {
        this.likeCount = likeCount;
    }

    public Long getLikeCount() 
    {
        return likeCount;
    }

    public void setHidden(Long hidden) 
    {
        this.hidden = hidden;
    }

    public Long getHidden() 
    {
        return hidden;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("feedbackId", getFeedbackId())
            .append("applyId", getApplyId())
            .append("content", getContent())
            .append("feedbackImage", getFeedbackImage())
            .append("feedbackTime", getFeedbackTime())
            .append("likeCount", getLikeCount())
            .append("hidden", getHidden())
            .toString();
    }
}
