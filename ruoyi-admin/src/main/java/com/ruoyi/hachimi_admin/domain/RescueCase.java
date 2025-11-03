package com.ruoyi.hachimi_admin.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 救助案例信息对象 rescue_case
 *
 * @author zhongyu
 * @date 2025-11-03
 */
public class RescueCase extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 案例ID */
    private Long caseId;

    /** 案例标题 */
    @Excel(name = "案例标题")
    private String title;

    /** 关联宠物ID */
    @Excel(name = "关联宠物ID")
    private Long petId;

    /** 关联宠物名称 */
    @Excel(name = "关联宠物名称")
    private String petName;

    /** 救助过程详情 */
    @Excel(name = "救助过程详情")
    private String content;

    /** 救助花费 */
    @Excel(name = "救助花费")
    private BigDecimal rescueCost;

    /** 案例状态：0-救助中；1-已完成 */
    @Excel(name = "案例状态", readConverterExp = "0=救助中,1=已完成")
    private Long status;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishTime;

    /** 发布人ID */
    @Excel(name = "发布人ID")
    private Long createUserId;

    /** 发布人名称 */
    @Excel(name = "发布人名称")
    private String createUserName;

    /** 浏览量 */
    @Excel(name = "浏览量")
    private Long viewCount;

    /** 是否隐藏：0-不隐藏；1-隐藏 */
    @Excel(name = "是否隐藏", readConverterExp = "0=不隐藏,1=隐藏")
    private Long hidden;

    /** 案例图片信息 */
    private List<CaseImage> caseImageList;

    public void setCaseId(Long caseId)
    {
        this.caseId = caseId;
    }

    public Long getCaseId()
    {
        return caseId;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTitle()
    {
        return title;
    }

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

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }

    public void setRescueCost(BigDecimal rescueCost)
    {
        this.rescueCost = rescueCost;
    }

    public BigDecimal getRescueCost()
    {
        return rescueCost;
    }

    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }

    public void setPublishTime(Date publishTime)
    {
        this.publishTime = publishTime;
    }

    public Date getPublishTime()
    {
        return publishTime;
    }

    public void setCreateUserId(Long createUserId)
    {
        this.createUserId = createUserId;
    }

    public Long getCreateUserId()
    {
        return createUserId;
    }

    public void setCreateUserName(String createUserName)
    {
        this.createUserName = createUserName;
    }

    public String getCreateUserName()
    {
        return createUserName;
    }

    public void setViewCount(Long viewCount)
    {
        this.viewCount = viewCount;
    }

    public Long getViewCount()
    {
        return viewCount;
    }

    public void setHidden(Long hidden)
    {
        this.hidden = hidden;
    }

    public Long getHidden()
    {
        return hidden;
    }

    public List<CaseImage> getCaseImageList()
    {
        return caseImageList;
    }

    public void setCaseImageList(List<CaseImage> caseImageList)
    {
        this.caseImageList = caseImageList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("caseId", getCaseId())
                .append("title", getTitle())
                .append("petId", getPetId())
                .append("petName", getPetName())
                .append("content", getContent())
                .append("rescueCost", getRescueCost())
                .append("status", getStatus())
                .append("publishTime", getPublishTime())
                .append("createUserId", getCreateUserId())
                .append("createUserName", getCreateUserName())
                .append("viewCount", getViewCount())
                .append("hidden", getHidden())
                .append("caseImageList", getCaseImageList())
                .toString();
    }
}