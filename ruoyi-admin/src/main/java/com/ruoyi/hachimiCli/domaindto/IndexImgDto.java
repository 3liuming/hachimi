package com.ruoyi.hachimiCli.domaindto;

public class IndexImgDto {
    /** 图片唯一ID */
     private Long imageId;

    /** 关联宠物ID */
    private Long petId;

    /** 宠物昵称 */
    private String petName;

    /** 图片URL地址 */
    private String imageUrl;

    /** 图片排序（1-第一张，按升序展示） */
    private Long sort;

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "IndexImgDto{" +
                "imageId=" + imageId +
                ", petId=" + petId +
                ", petName='" + petName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", sort=" + sort +
                '}';
    }
}
