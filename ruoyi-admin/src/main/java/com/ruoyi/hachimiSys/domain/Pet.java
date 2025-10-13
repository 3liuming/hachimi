package com.ruoyi.hachimiSys.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物信息对象 pet
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public class Pet extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 宠物唯一ID */
    @Excel(name = "宠物唯一ID")
    private Long petId;

    /** 宠物昵称 */
    @Excel(name = "宠物昵称")
    private String petName;

    /** 年龄（单位：月） */
    @Excel(name = "年龄", readConverterExp = "单=位：月")
    private Long age;

    /** 性别：0-公；1-母 */
    @Excel(name = "性别：0-公；1-母")
    private Long gender;

    /** 健康状况简述 */
    @Excel(name = "健康状况简述")
    private String healthStatus;

    /** 性格描述（如：温顺、活泼） */
    @Excel(name = "性格描述", readConverterExp = "如=：温顺、活泼")
    private String personality;

    /** 救助时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "救助时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date rescueTime;

    /** 救助地点 */
    @Excel(name = "救助地点")
    private String rescuePlace;

    /** 状态：0-待领养；1-已领养；2-救助中；3-已救助（非领养） */
    @Excel(name = "状态：0-待领养；1-已领养；2-救助中；3-已救助", readConverterExp = "非=领养")
    private Long status;

    /** 宠物封面图URL（列表页展示） */
    @Excel(name = "宠物封面图URL", readConverterExp = "列=表页展示")
    private String coverImage;

    /** 是否隐藏：0-不隐藏；1-隐藏 */
    @Excel(name = "是否隐藏：0-不隐藏；1-隐藏")
    private Long hidden;

    /** 关联品种ID（来自breed表） */
    @Excel(name = "关联品种ID", readConverterExp = "来=自breed表")
    private Long breedId;

    public void setPetId(Long petId) 
    {
        this.petId = petId;
    }

    public Long getPetId() 
    {
        return petId;
    }

    public void setPetName(String petName) 
    {
        this.petName = petName;
    }

    public String getPetName() 
    {
        return petName;
    }

    public void setAge(Long age) 
    {
        this.age = age;
    }

    public Long getAge() 
    {
        return age;
    }

    public void setGender(Long gender) 
    {
        this.gender = gender;
    }

    public Long getGender() 
    {
        return gender;
    }

    public void setHealthStatus(String healthStatus) 
    {
        this.healthStatus = healthStatus;
    }

    public String getHealthStatus() 
    {
        return healthStatus;
    }

    public void setPersonality(String personality) 
    {
        this.personality = personality;
    }

    public String getPersonality() 
    {
        return personality;
    }

    public void setRescueTime(Date rescueTime) 
    {
        this.rescueTime = rescueTime;
    }

    public Date getRescueTime() 
    {
        return rescueTime;
    }

    public void setRescuePlace(String rescuePlace) 
    {
        this.rescuePlace = rescuePlace;
    }

    public String getRescuePlace() 
    {
        return rescuePlace;
    }

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    public void setCoverImage(String coverImage) 
    {
        this.coverImage = coverImage;
    }

    public String getCoverImage() 
    {
        return coverImage;
    }

    public void setHidden(Long hidden) 
    {
        this.hidden = hidden;
    }

    public Long getHidden() 
    {
        return hidden;
    }

    public void setBreedId(Long breedId) 
    {
        this.breedId = breedId;
    }

    public Long getBreedId() 
    {
        return breedId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("petId", getPetId())
            .append("petName", getPetName())
            .append("age", getAge())
            .append("gender", getGender())
            .append("healthStatus", getHealthStatus())
            .append("personality", getPersonality())
            .append("rescueTime", getRescueTime())
            .append("rescuePlace", getRescuePlace())
            .append("status", getStatus())
            .append("coverImage", getCoverImage())
            .append("hidden", getHidden())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("breedId", getBreedId())
            .toString();
    }
}
