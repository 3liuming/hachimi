package com.ruoyi.hachimiSys.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hachimiSys.mapper.UserMapper;
import com.ruoyi.hachimiSys.domain.User;
import com.ruoyi.hachimiSys.service.IUserService;

/**
 * 用户（普通用户+管理员）Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@Service
public class UserServiceImpl implements IUserService 
{
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询用户（普通用户+管理员）
     * 
     * @param userId 用户（普通用户+管理员）主键
     * @return 用户（普通用户+管理员）
     */
    @Override
    public User selectUserByUserId(Long userId)
    {
        return userMapper.selectUserByUserId(userId);
    }

    /**
     * 查询用户（普通用户+管理员）列表
     * 
     * @param user 用户（普通用户+管理员）
     * @return 用户（普通用户+管理员）
     */
    @Override
    public List<User> selectUserList(User user)
    {
        return userMapper.selectUserList(user);
    }

    /**
     * 新增用户（普通用户+管理员）
     * 
     * @param user 用户（普通用户+管理员）
     * @return 结果
     */
    @Override
    public int insertUser(User user)
    {
        user.setCreateTime(DateUtils.getNowDate());
        return userMapper.insertUser(user);
    }

    /**
     * 修改用户（普通用户+管理员）
     * 
     * @param user 用户（普通用户+管理员）
     * @return 结果
     */
    @Override
    public int updateUser(User user)
    {
        user.setUpdateTime(DateUtils.getNowDate());
        return userMapper.updateUser(user);
    }

    /**
     * 批量删除用户（普通用户+管理员）
     * 
     * @param userIds 需要删除的用户（普通用户+管理员）主键
     * @return 结果
     */
    @Override
    public int deleteUserByUserIds(Long[] userIds)
    {
        return userMapper.deleteUserByUserIds(userIds);
    }

    /**
     * 删除用户（普通用户+管理员）信息
     * 
     * @param userId 用户（普通用户+管理员）主键
     * @return 结果
     */
    @Override
    public int deleteUserByUserId(Long userId)
    {
        return userMapper.deleteUserByUserId(userId);
    }
}
