package com.ruoyi.hachimiSys.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物图片信息对象 pet_image
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public class PetImage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图片唯一ID */
    @Excel(name = "图片唯一ID")
    private Long imageId;

    /** 关联宠物ID */
    @Excel(name = "关联宠物ID")
    private Long petId;

    /** 图片URL地址 */
    @Excel(name = "图片URL地址")
    private String imageUrl;

    /** 图片排序（1-第一张，按升序展示） */
    @Excel(name = "图片排序", readConverterExp = "1=-第一张，按升序展示")
    private Long sort;

    /** 是否隐藏：0-不隐藏；1-隐藏 */
    @Excel(name = "是否隐藏：0-不隐藏；1-隐藏")
    private Long hidden;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    public void setImageId(Long imageId) 
    {
        this.imageId = imageId;
    }

    public Long getImageId() 
    {
        return imageId;
    }

    public void setPetId(Long petId) 
    {
        this.petId = petId;
    }

    public Long getPetId() 
    {
        return petId;
    }

    public void setImageUrl(String imageUrl) 
    {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() 
    {
        return imageUrl;
    }

    public void setSort(Long sort) 
    {
        this.sort = sort;
    }

    public Long getSort() 
    {
        return sort;
    }

    public void setHidden(Long hidden) 
    {
        this.hidden = hidden;
    }

    public Long getHidden() 
    {
        return hidden;
    }

    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("imageId", getImageId())
            .append("petId", getPetId())
            .append("imageUrl", getImageUrl())
            .append("sort", getSort())
            .append("hidden", getHidden())
            .append("uploadTime", getUploadTime())
            .toString();
    }
}
