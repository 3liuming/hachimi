package com.ruoyi.hachimiSys.mapper;

import java.util.List;
import com.ruoyi.hachimiSys.domain.AdoptFeedback;

/**
 * 领养后反馈信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public interface AdoptFeedbackMapper 
{
    /**
     * 查询领养后反馈信息
     * 
     * @param feedbackId 领养后反馈信息主键
     * @return 领养后反馈信息
     */
    public AdoptFeedback selectAdoptFeedbackByFeedbackId(Long feedbackId);

    /**
     * 查询领养后反馈信息列表
     * 
     * @param adoptFeedback 领养后反馈信息
     * @return 领养后反馈信息集合
     */
    public List<AdoptFeedback> selectAdoptFeedbackList(AdoptFeedback adoptFeedback);

    /**
     * 新增领养后反馈信息
     * 
     * @param adoptFeedback 领养后反馈信息
     * @return 结果
     */
    public int insertAdoptFeedback(AdoptFeedback adoptFeedback);

    /**
     * 修改领养后反馈信息
     * 
     * @param adoptFeedback 领养后反馈信息
     * @return 结果
     */
    public int updateAdoptFeedback(AdoptFeedback adoptFeedback);

    /**
     * 删除领养后反馈信息
     * 
     * @param feedbackId 领养后反馈信息主键
     * @return 结果
     */
    public int deleteAdoptFeedbackByFeedbackId(Long feedbackId);

    /**
     * 批量删除领养后反馈信息
     * 
     * @param feedbackIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdoptFeedbackByFeedbackIds(Long[] feedbackIds);
}
