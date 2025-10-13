package com.ruoyi.hachimiCli.domaindto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AdoptionApplyDto {

    /** 申请唯一ID */
    private Long applyId;

    /** 申请人ID（普通用户） */
    private Long userId;

    /** 申请领养的宠物ID */
    private Long petId;

    /** 领养理由（居住环境、饲养经验等） */
    private String applyReason;

    /** 申请提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date applyTime;

    /** 审核人ID（管理员） */
    private Long auditUserId;

    /** 审核状态：0-待审核；1-通过；2-拒绝 */
    private Long auditStatus;

    /** 审核备注（如拒绝原因） */
    private String auditRemark;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date auditTime;

    /** 实际领养时间（审核通过后填写） */
    private Date adoptTime;

    public Long getApplyId() {
        return applyId;
    }

    public void setApplyId(Long applyId) {
        this.applyId = applyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Long getAuditUserId() {
        return auditUserId;
    }

    public void setAuditUserId(Long auditUserId) {
        this.auditUserId = auditUserId;
    }

    public Long getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Long auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getAdoptTime() {
        return adoptTime;
    }

    public void setAdoptTime(Date adoptTime) {
        this.adoptTime = adoptTime;
    }

    @Override
    public String toString() {
        return "AdoptionApplyDto{" +
                "applyId=" + applyId +
                ", userId=" + userId +
                ", petId=" + petId +
                ", applyReason='" + applyReason + '\'' +
                ", applyTime=" + applyTime +
                ", auditUserId=" + auditUserId +
                ", auditStatus=" + auditStatus +
                ", auditRemark='" + auditRemark + '\'' +
                ", auditTime=" + auditTime +
                ", adoptTime=" + adoptTime +
                '}';
    }
}
