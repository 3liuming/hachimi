package com.ruoyi.hachimiSys.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物健康档案信息对象 pet_health_record
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public class PetHealthRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 健康记录ID */
    @Excel(name = "健康记录ID")
    private Long healthId;

    /** 关联宠物ID */
    @Excel(name = "关联宠物ID")
    private Long petId;

    /** 记录类型：0-疫苗；1-体检；2-疾病治疗 */
    @Excel(name = "记录类型：0-疫苗；1-体检；2-疾病治疗")
    private Long recordType;

    /** 记录内容（如：狂犬疫苗，2023-10-01） */
    @Excel(name = "记录内容", readConverterExp = "如=：狂犬疫苗，2023-10-01")
    private String content;

    /** 医生姓名 */
    @Excel(name = "医生姓名")
    private String doctor;

    /** 医院/机构名称 */
    @Excel(name = "医院/机构名称")
    private String hospital;

    /** 记录发生时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "记录发生时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recordTime;

    /** 录入人ID（管理员） */
    @Excel(name = "录入人ID", readConverterExp = "管=理员")
    private Long createUserId;

    /** 是否隐藏：0-不隐藏；1-隐藏 */
    @Excel(name = "是否隐藏：0-不隐藏；1-隐藏")
    private Long hidden;

    public void setHealthId(Long healthId) 
    {
        this.healthId = healthId;
    }

    public Long getHealthId() 
    {
        return healthId;
    }

    public void setPetId(Long petId) 
    {
        this.petId = petId;
    }

    public Long getPetId() 
    {
        return petId;
    }

    public void setRecordType(Long recordType) 
    {
        this.recordType = recordType;
    }

    public Long getRecordType() 
    {
        return recordType;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setDoctor(String doctor) 
    {
        this.doctor = doctor;
    }

    public String getDoctor() 
    {
        return doctor;
    }

    public void setHospital(String hospital) 
    {
        this.hospital = hospital;
    }

    public String getHospital() 
    {
        return hospital;
    }

    public void setRecordTime(Date recordTime) 
    {
        this.recordTime = recordTime;
    }

    public Date getRecordTime() 
    {
        return recordTime;
    }

    public void setCreateUserId(Long createUserId) 
    {
        this.createUserId = createUserId;
    }

    public Long getCreateUserId() 
    {
        return createUserId;
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
            .append("healthId", getHealthId())
            .append("petId", getPetId())
            .append("recordType", getRecordType())
            .append("content", getContent())
            .append("doctor", getDoctor())
            .append("hospital", getHospital())
            .append("recordTime", getRecordTime())
            .append("createUserId", getCreateUserId())
            .append("hidden", getHidden())
            .append("createTime", getCreateTime())
            .toString();
    }
}
