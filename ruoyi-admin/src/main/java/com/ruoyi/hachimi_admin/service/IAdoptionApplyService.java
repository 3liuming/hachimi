package com.ruoyi.hachimi_admin.service;

import java.util.List;
import com.ruoyi.hachimi_admin.domain.AdoptionApply;

/**
 * 领养申请Service接口
 * 
 * @author zhongyu
 * @date 2025-11-03
 */
public interface IAdoptionApplyService 
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
     * 批量删除领养申请
     * 
     * @param applyIds 需要删除的领养申请主键集合
     * @return 结果
     */
    public int deleteAdoptionApplyByApplyIds(Long[] applyIds);

    /**
     * 删除领养申请信息
     * 
     * @param applyId 领养申请主键
     * @return 结果
     */
    public int deleteAdoptionApplyByApplyId(Long applyId);
}
