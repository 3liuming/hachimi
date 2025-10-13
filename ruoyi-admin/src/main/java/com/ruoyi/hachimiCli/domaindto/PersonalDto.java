package com.ruoyi.hachimiCli.domaindto;

public class PersonalDto {

    /** 用户唯一ID */
    private Long userId;

    /** 加密后的密码（建议MD5/SHA256） */
    private String password;

    /** 真实姓名（用于领养身份验证） */
    private String realName;

    /** 身份证号（领养时验证，可选） */
    private String idCard;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        return "PersonalDto{" +
                "userId=" + userId +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", idCard='" + idCard + '\'' +
                '}';
    }
}
