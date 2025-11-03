package com.ruoyi.hachimi_admin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户信息对象 user
 * 
 * @author zhongyu
 * @date 2025-11-03
 */
public class User extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户唯一ID */
    private Long userId;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickname;

    /** 账户 */
    @Excel(name = "账户")
    private String username;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String realName;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idCard;

    /** 居住地址 */
    @Excel(name = "居住地址")
    private String address;

    /** 级别：0-普通用户；1-管理员 */
    @Excel(name = "级别：0-普通用户；1-管理员")
    private Long role;

    /** 账号状态：0-正常；1-禁用 */
    @Excel(name = "账号状态：0-正常；1-禁用")
    private Long status;

    /** 是否隐藏：0-不隐藏；1-隐藏 */
    @Excel(name = "是否隐藏：0-不隐藏；1-隐藏")
    private Long hidden;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }

    public void setNickname(String nickname) 
    {
        this.nickname = nickname;
    }

    public String getNickname() 
    {
        return nickname;
    }

    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }

    public void setRealName(String realName) 
    {
        this.realName = realName;
    }

    public String getRealName() 
    {
        return realName;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setRole(Long role) 
    {
        this.role = role;
    }

    public Long getRole() 
    {
        return role;
    }

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
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
            .append("userId", getUserId())
            .append("nickname", getNickname())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("realName", getRealName())
            .append("phone", getPhone())
            .append("idCard", getIdCard())
            .append("address", getAddress())
            .append("role", getRole())
            .append("status", getStatus())
            .append("hidden", getHidden())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
