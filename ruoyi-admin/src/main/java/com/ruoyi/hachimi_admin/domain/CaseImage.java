package com.ruoyi.hachimi_admin.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 案例图片对象 case_image
 * 
 * @author zhognyu
 * @date 2025-11-03
 */
public class CaseImage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 案例图片ID */
    private Long caseImageId;

    /** 关联案例ID */
    @Excel(name = "关联案例ID")
    private Long caseId;

    /** 图片URL地址 */
    @Excel(name = "图片URL地址")
    private String imageUrl;

    /** 图片排序（按升序展示） */
    @Excel(name = "图片排序", readConverterExp = "按=升序展示")
    private Long sort;

    /** 是否隐藏：0-不隐藏；1-隐藏 */
    @Excel(name = "是否隐藏：0-不隐藏；1-隐藏")
    private Long hidden;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    public void setCaseImageId(Long caseImageId) 
    {
        this.caseImageId = caseImageId;
    }

    public Long getCaseImageId() 
    {
        return caseImageId;
    }

    public void setCaseId(Long caseId) 
    {
        this.caseId = caseId;
    }

    public Long getCaseId() 
    {
        return caseId;
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
            .append("caseImageId", getCaseImageId())
            .append("caseId", getCaseId())
            .append("imageUrl", getImageUrl())
            .append("sort", getSort())
            .append("hidden", getHidden())
            .append("uploadTime", getUploadTime())
            .toString();
    }
}
