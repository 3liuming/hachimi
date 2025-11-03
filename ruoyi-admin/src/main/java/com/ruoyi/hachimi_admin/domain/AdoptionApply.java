package com.ruoyi.hachimi_admin.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 领养申请对象 adoption_apply
 * 
 * @author zhongyu
 * @date 2025-11-03
 */
public class AdoptionApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申请唯一ID */
    private Long applyId;

    /** 申请人ID */
    @Excel(name = "申请人ID")
    private Long userId;

    /** 申请领养的宠物ID */
    @Excel(name = "申请领养的宠物ID")
    private Long petId;

    /** 领养理由 */
    @Excel(name = "领养理由")
    private String applyReason;

    /** 申请提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applyTime;

    /** 审核人ID */
    @Excel(name = "审核人ID")
    private Long auditUserId;

    /** 审核状态：0-待审核；1-通过；2-拒绝 */
    @Excel(name = "审核状态：0-待审核；1-通过；2-拒绝")
    private Long auditStatus;

    /** 审核备注 */
    @Excel(name = "审核备注")
    private String auditRemark;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auditTime;

    /** 实际领养时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际领养时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date adoptTime;

    /** 是否隐藏：0-不隐藏；1-隐藏 */
    @Excel(name = "是否隐藏：0-不隐藏；1-隐藏")
    private Long hidden;

    /** 领养后反馈信息 */
    private List<AdoptFeedback> adoptFeedbackList;

    public void setApplyId(Long applyId) 
    {
        this.applyId = applyId;
    }

    public Long getApplyId() 
    {
        return applyId;
    }

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setPetId(Long petId) 
    {
        this.petId = petId;
    }

    public Long getPetId() 
    {
        return petId;
    }

    public void setApplyReason(String applyReason) 
    {
        this.applyReason = applyReason;
    }

    public String getApplyReason() 
    {
        return applyReason;
    }

    public void setApplyTime(Date applyTime) 
    {
        this.applyTime = applyTime;
    }

    public Date getApplyTime() 
    {
        return applyTime;
    }

    public void setAuditUserId(Long auditUserId) 
    {
        this.auditUserId = auditUserId;
    }

    public Long getAuditUserId() 
    {
        return auditUserId;
    }

    public void setAuditStatus(Long auditStatus) 
    {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() 
    {
        return auditStatus;
    }

    public void setAuditRemark(String auditRemark) 
    {
        this.auditRemark = auditRemark;
    }

    public String getAuditRemark() 
    {
        return auditRemark;
    }

    public void setAuditTime(Date auditTime) 
    {
        this.auditTime = auditTime;
    }

    public Date getAuditTime() 
    {
        return auditTime;
    }

    public void setAdoptTime(Date adoptTime) 
    {
        this.adoptTime = adoptTime;
    }

    public Date getAdoptTime() 
    {
        return adoptTime;
    }

    public void setHidden(Long hidden) 
    {
        this.hidden = hidden;
    }

    public Long getHidden() 
    {
        return hidden;
    }

    public List<AdoptFeedback> getAdoptFeedbackList()
    {
        return adoptFeedbackList;
    }

    public void setAdoptFeedbackList(List<AdoptFeedback> adoptFeedbackList)
    {
        this.adoptFeedbackList = adoptFeedbackList;
    }



    /** 申请人账号 */
    @Excel(name = "申请人账号")
    private String username;


    /** 宠物昵称 */
    @Excel(name = "宠物昵称")
    private String petName;


    /** 审核人账号 */
    @Excel(name = "审核人账号")
    private String auditUsername;

    // 在对应的 getter/setter 后添加新字段的方法
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }

    public void setPetName(String petName)
    {
        this.petName = petName;
    }

    public String getPetName()
    {
        return petName;
    }

    public void setAuditUsername(String auditUsername)
    {
        this.auditUsername = auditUsername;
    }

    public String getAuditUsername()
    {
        return auditUsername;
    }

    // 修改 toString() 方法，添加新字段
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("applyId", getApplyId())
                .append("userId", getUserId())
                .append("username", getUsername())
                .append("petId", getPetId())
                .append("petName", getPetName())
                .append("applyReason", getApplyReason())
                .append("applyTime", getApplyTime())
                .append("auditUserId", getAuditUserId())
                .append("auditUsername", getAuditUsername())
                .append("auditStatus", getAuditStatus())
                .append("auditRemark", getAuditRemark())
                .append("auditTime", getAuditTime())
                .append("adoptTime", getAdoptTime())
                .append("hidden", getHidden())
                .append("adoptFeedbackList", getAdoptFeedbackList())
                .toString();
    }
}
