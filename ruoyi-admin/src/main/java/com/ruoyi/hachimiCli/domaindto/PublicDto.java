package com.ruoyi.hachimiCli.domaindto;

import com.ruoyi.common.annotation.Excel;

public class PublicDto {

    /** 用户唯一ID */
    private Long userId;

    /** 昵称 */
    private String nickname;

    /** 登录用户名（唯一） */
    private String username;

    /** 联系电话（唯一） */
    private String phone;

    /** 居住地址（评估领养条件） */
    private String address;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PublicDto{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
