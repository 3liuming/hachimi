package com.ruoyi.hachimi_admin.mapper;

import java.util.List;
import com.ruoyi.hachimi_admin.domain.AdoptFeedback;

/**
 * 领养后反馈Mapper接口
 * 
 * @author zhongyu
 * @date 2025-11-03
 */
public interface AdoptFeedbackMapper 
{
    /**
     * 查询领养后反馈
     * 
     * @param feedbackId 领养后反馈主键
     * @return 领养后反馈
     */
    public AdoptFeedback selectAdoptFeedbackByFeedbackId(Long feedbackId);

    /**
     * 查询领养后反馈列表
     * 
     * @param adoptFeedback 领养后反馈
     * @return 领养后反馈集合
     */
    public List<AdoptFeedback> selectAdoptFeedbackList(AdoptFeedback adoptFeedback);

    /**
     * 新增领养后反馈
     * 
     * @param adoptFeedback 领养后反馈
     * @return 结果
     */
    public int insertAdoptFeedback(AdoptFeedback adoptFeedback);

    /**
     * 修改领养后反馈
     * 
     * @param adoptFeedback 领养后反馈
     * @return 结果
     */
    public int updateAdoptFeedback(AdoptFeedback adoptFeedback);

    /**
     * 删除领养后反馈
     * 
     * @param feedbackId 领养后反馈主键
     * @return 结果
     */
    public int deleteAdoptFeedbackByFeedbackId(Long feedbackId);

    /**
     * 批量删除领养后反馈
     * 
     * @param feedbackIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdoptFeedbackByFeedbackIds(Long[] feedbackIds);
}
