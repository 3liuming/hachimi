package com.ruoyi.hachimiSys.mapper;

import java.util.List;
import com.ruoyi.hachimiSys.domain.User;

/**
 * 用户（普通用户+管理员）Mapper接口
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public interface UserMapper 
{
    /**
     * 查询用户（普通用户+管理员）
     * 
     * @param userId 用户（普通用户+管理员）主键
     * @return 用户（普通用户+管理员）
     */
    public User selectUserByUserId(Long userId);

    /**
     * 查询用户（普通用户+管理员）列表
     * 
     * @param user 用户（普通用户+管理员）
     * @return 用户（普通用户+管理员）集合
     */
    public List<User> selectUserList(User user);

    /**
     * 新增用户（普通用户+管理员）
     * 
     * @param user 用户（普通用户+管理员）
     * @return 结果
     */
    public int insertUser(User user);

    /**
     * 修改用户（普通用户+管理员）
     * 
     * @param user 用户（普通用户+管理员）
     * @return 结果
     */
    public int updateUser(User user);

    /**
     * 删除用户（普通用户+管理员）
     * 
     * @param userId 用户（普通用户+管理员）主键
     * @return 结果
     */
    public int deleteUserByUserId(Long userId);

    /**
     * 批量删除用户（普通用户+管理员）
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserByUserIds(Long[] userIds);
}
