package com.ruoyi.hachimiCli.domaindto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PetAndImgDto {

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    // 新增:分页字段
    private Integer pageNum;  // 当前页码
    private Integer pageSize; // 每页条数

    /** 宠物唯一ID */
    private Long petId;

    /** 宠物昵称 */
    private String petName;

    /** 年龄（单位：月） */
    private Long age;

    /** 性别：0-公；1-母 */
    private Long gender;

    /** 健康状况简述 */
    private String healthStatus;

    /** 性格描述（如：温顺、活泼） */
    private String personality;

    /** 救助时间 */
    private Date rescueTime;

    /** 救助地点 */
    private String rescuePlace;

    /** 状态：0-待领养；1-已领养；2-救助中；3-已救助（非领养） */
    private Long status;

    /** 关联品种ID（来自breed表） */
    private Long breedId;

    /** 品种名称（如：金毛、布偶猫） */
    private String breedName;

    /** 所属物种（如：狗、猫） */
    private String species;

    /** 图片唯一ID */
    private Long imageId;

    /** 图片URL地址 */
    private String imageUrl;

    /** 图片排序（1-第一张，按升序展示） */
    private Long sort;

    /** 图片上传时间 */
    private Date uploadTime;

    /** 健康记录ID */
    private Long healthId;

    /** 记录类型：0-疫苗；1-体检；2-疾病治疗 */
    private Long recordType;

    /** 记录内容（如：狂犬疫苗，2023-10-01） */
    private String content;

    /** 医生姓名 */
     private String doctor;

    /** 医院/机构名称 */
     private String hospital;

    /** 记录发生时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
     private Date recordTime;

    /** 录入人ID（管理员） */
    private Long createUserId;

    // Getters and Setters
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

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getGender() {
        return gender;
    }

    public void setGender(Long gender) {
        this.gender = gender;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public Date getRescueTime() {
        return rescueTime;
    }

    public void setRescueTime(Date rescueTime) {
        this.rescueTime = rescueTime;
    }

    public String getRescuePlace() {
        return rescuePlace;
    }

    public void setRescuePlace(String rescuePlace) {
        this.rescuePlace = rescuePlace;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getBreedId() {
        return breedId;
    }

    public void setBreedId(Long breedId) {
        this.breedId = breedId;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
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

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Long getHealthId() {
        return healthId;
    }

    public void setHealthId(Long healthId) {
        this.healthId = healthId;
    }

    public Long getRecordType() {
        return recordType;
    }

    public void setRecordType(Long recordType) {
        this.recordType = recordType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    @Override
    public String toString() {
        return "PetAndImgDto{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", petId=" + petId +
                ", petName='" + petName + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", healthStatus='" + healthStatus + '\'' +
                ", personality='" + personality + '\'' +
                ", rescueTime=" + rescueTime +
                ", rescuePlace='" + rescuePlace + '\'' +
                ", status=" + status +
                ", breedId=" + breedId +
                ", breedName='" + breedName + '\'' +
                ", species='" + species + '\'' +
                ", imageId=" + imageId +
                ", imageUrl='" + imageUrl + '\'' +
                ", sort=" + sort +
                ", uploadTime=" + uploadTime +
                ", healthId=" + healthId +
                ", recordType=" + recordType +
                ", content='" + content + '\'' +
                ", doctor='" + doctor + '\'' +
                ", hospital='" + hospital + '\'' +
                ", recordTime=" + recordTime +
                ", createUserId=" + createUserId +
                '}';
    }
}