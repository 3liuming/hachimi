package com.ruoyi.hachimiCli.domaindto;

import java.math.BigDecimal;
import java.util.Date;

public class CaseAndImgDto {

    /** 案例ID */
    private Long caseId;

    /** 案例标题（如：“流浪猫救助记”） */
    private String title;

    /** 救助过程详情（支持HTML/Markdown） */
    private String content;

    /** 救助花费（公开透明度） */
    private BigDecimal rescueCost;

    /** 案例状态：0-救助中；1-已完成 */
     private Long status;

    /** 发布时间 */
    private Date publishTime;

    /** 发布人 */
    private String nickname;

    /** 浏览量 */
    private Long viewCount;

    /** 宠物昵称 */
    private String petName;

    /** 救助地点 */
    private String rescuePlace;

    /** 图片URL地址 */
    private String imageUrl;

    public Long getCaseId() {
        return caseId;
    }

    public void setCaseId(Long caseId) {
        this.caseId = caseId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getRescueCost() {
        return rescueCost;
    }

    public void setRescueCost(BigDecimal rescueCost) {
        this.rescueCost = rescueCost;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getNickName() {
        return nickname;
    }

    public void setNickName(String nickname) {
        this.nickname = nickname;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getRescuePlace() {
        return rescuePlace;
    }

    public void setRescuePlace(String rescuePlace) {
        this.rescuePlace = rescuePlace;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "CaseAndImgDto{" +
                "caseId=" + caseId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", rescueCost=" + rescueCost +
                ", status=" + status +
                ", publishTime=" + publishTime +
                ", nickname=" + nickname +
                ", viewCount=" + viewCount +
                ", petName='" + petName + '\'' +
                ", rescuePlace='" + rescuePlace + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
