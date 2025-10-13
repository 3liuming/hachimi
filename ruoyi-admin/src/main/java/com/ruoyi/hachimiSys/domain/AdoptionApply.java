package com.ruoyi.hachimiSys.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 领养申请信息对象 adoption_apply
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public class AdoptionApply extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申请唯一ID */
    @Excel(name = "申请唯一ID")
    private Long applyId;

    /** 申请人ID（普通用户） */
    @Excel(name = "申请人ID", readConverterExp = "普=通用户")
    private Long userId;

    /** 申请领养的宠物ID */
    @Excel(name = "申请领养的宠物ID")
    private Long petId;

    /** 领养理由（居住环境、饲养经验等） */
    @Excel(name = "领养理由", readConverterExp = "居=住环境、饲养经验等")
    private String applyReason;

    /** 申请提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applyTime;

    /** 审核人ID（管理员） */
    @Excel(name = "审核人ID", readConverterExp = "管=理员")
    private Long auditUserId;

    /** 审核状态：0-待审核；1-通过；2-拒绝 */
    @Excel(name = "审核状态：0-待审核；1-通过；2-拒绝")
    private Long auditStatus;

    /** 审核备注（如拒绝原因） */
    @Excel(name = "审核备注", readConverterExp = "如=拒绝原因")
    private String auditRemark;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date auditTime;

    /** 实际领养时间（审核通过后填写） */
    @Excel(name = "实际领养时间", readConverterExp = "审=核通过后填写")
    private Date adoptTime;

    /** 是否隐藏：0-不隐藏；1-隐藏 */
    @Excel(name = "是否隐藏：0-不隐藏；1-隐藏")
    private Long hidden;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("applyId", getApplyId())
            .append("userId", getUserId())
            .append("petId", getPetId())
            .append("applyReason", getApplyReason())
            .append("applyTime", getApplyTime())
            .append("auditUserId", getAuditUserId())
            .append("auditStatus", getAuditStatus())
            .append("auditRemark", getAuditRemark())
            .append("auditTime", getAuditTime())
            .append("adoptTime", getAdoptTime())
            .append("hidden", getHidden())
            .toString();
    }
}
