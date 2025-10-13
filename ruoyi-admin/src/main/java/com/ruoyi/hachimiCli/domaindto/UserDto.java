package com.ruoyi.hachimiCli.domaindto;


public class UserDto {
    /** 用户唯一ID */
    private Long userId;

    /** 登录用户名（唯一） */
    private String username;

    /** 密码 */
    private String password;

    /** 联系电话（唯一） */
   private String phone;

    /** 角色：0-普通用户；1-管理员 */
    private Long role;

    /** 账号状态：0-正常；1-禁用 */
    private Long status;

    /** 是否隐藏：0-不隐藏；1-隐藏 */
     private Long hidden;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getHidden() {
        return hidden;
    }

    public void setHidden(Long hidden) {
        this.hidden = hidden;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", hidden=" + hidden +
                '}';
    }
}
