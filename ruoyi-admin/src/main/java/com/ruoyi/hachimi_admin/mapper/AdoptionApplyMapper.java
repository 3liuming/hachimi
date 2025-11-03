package com.ruoyi.hachimi_admin.mapper;

import java.util.List;
import com.ruoyi.hachimi_admin.domain.AdoptionApply;
import com.ruoyi.hachimi_admin.domain.AdoptFeedback;

/**
 * 领养申请Mapper接口
 * 
 * @author zhongyu
 * @date 2025-11-03
 */
public interface AdoptionApplyMapper 
{
    /**
     * 查询领养申请
     * 
     * @param applyId 领养申请主键
     * @return 领养申请
     */
    public AdoptionApply selectAdoptionApplyByApplyId(Long applyId);

    /**
     * 查询领养申请列表
     * 
     * @param adoptionApply 领养申请
     * @return 领养申请集合
     */
    public List<AdoptionApply> selectAdoptionApplyList(AdoptionApply adoptionApply);

    /**
     * 新增领养申请
     * 
     * @param adoptionApply 领养申请
     * @return 结果
     */
    public int insertAdoptionApply(AdoptionApply adoptionApply);

    /**
     * 修改领养申请
     * 
     * @param adoptionApply 领养申请
     * @return 结果
     */
    public int updateAdoptionApply(AdoptionApply adoptionApply);

    /**
     * 删除领养申请
     * 
     * @param applyId 领养申请主键
     * @return 结果
     */
    public int deleteAdoptionApplyByApplyId(Long applyId);

    /**
     * 批量删除领养申请
     * 
     * @param applyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdoptionApplyByApplyIds(Long[] applyIds);

    /**
     * 批量删除领养后反馈
     * 
     * @param applyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdoptFeedbackByApplyIds(Long[] applyIds);
    
    /**
     * 批量新增领养后反馈
     * 
     * @param adoptFeedbackList 领养后反馈列表
     * @return 结果
     */
    public int batchAdoptFeedback(List<AdoptFeedback> adoptFeedbackList);
    

    /**
     * 通过领养申请主键删除领养后反馈信息
     * 
     * @param applyId 领养申请ID
     * @return 结果
     */
    public int deleteAdoptFeedbackByApplyId(Long applyId);
}
