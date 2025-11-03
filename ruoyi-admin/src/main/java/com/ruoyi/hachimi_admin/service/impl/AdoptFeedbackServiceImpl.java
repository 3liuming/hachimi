package com.ruoyi.hachimi_admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hachimi_admin.mapper.AdoptFeedbackMapper;
import com.ruoyi.hachimi_admin.domain.AdoptFeedback;
import com.ruoyi.hachimi_admin.service.IAdoptFeedbackService;

/**
 * 领养后反馈Service业务层处理
 * 
 * @author zhongyu
 * @date 2025-11-03
 */
@Service
public class AdoptFeedbackServiceImpl implements IAdoptFeedbackService 
{
    @Autowired
    private AdoptFeedbackMapper adoptFeedbackMapper;

    /**
     * 查询领养后反馈
     * 
     * @param feedbackId 领养后反馈主键
     * @return 领养后反馈
     */
    @Override
    public AdoptFeedback selectAdoptFeedbackByFeedbackId(Long feedbackId)
    {
        return adoptFeedbackMapper.selectAdoptFeedbackByFeedbackId(feedbackId);
    }

    /**
     * 查询领养后反馈列表
     * 
     * @param adoptFeedback 领养后反馈
     * @return 领养后反馈
     */
    @Override
    public List<AdoptFeedback> selectAdoptFeedbackList(AdoptFeedback adoptFeedback)
    {
        return adoptFeedbackMapper.selectAdoptFeedbackList(adoptFeedback);
    }

    /**
     * 新增领养后反馈
     * 
     * @param adoptFeedback 领养后反馈
     * @return 结果
     */
    @Override
    public int insertAdoptFeedback(AdoptFeedback adoptFeedback)
    {
        return adoptFeedbackMapper.insertAdoptFeedback(adoptFeedback);
    }

    /**
     * 修改领养后反馈
     * 
     * @param adoptFeedback 领养后反馈
     * @return 结果
     */
    @Override
    public int updateAdoptFeedback(AdoptFeedback adoptFeedback)
    {
        return adoptFeedbackMapper.updateAdoptFeedback(adoptFeedback);
    }

    /**
     * 批量删除领养后反馈
     * 
     * @param feedbackIds 需要删除的领养后反馈主键
     * @return 结果
     */
    @Override
    public int deleteAdoptFeedbackByFeedbackIds(Long[] feedbackIds)
    {
        return adoptFeedbackMapper.deleteAdoptFeedbackByFeedbackIds(feedbackIds);
    }

    /**
     * 删除领养后反馈信息
     * 
     * @param feedbackId 领养后反馈主键
     * @return 结果
     */
    @Override
    public int deleteAdoptFeedbackByFeedbackId(Long feedbackId)
    {
        return adoptFeedbackMapper.deleteAdoptFeedbackByFeedbackId(feedbackId);
    }
}
