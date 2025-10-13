package com.ruoyi.hachimiSys.mapper;

import java.util.List;
import com.ruoyi.hachimiSys.domain.AdoptionApply;

/**
 * 领养申请信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public interface AdoptionApplyMapper 
{
    /**
     * 查询领养申请信息
     * 
     * @param applyId 领养申请信息主键
     * @return 领养申请信息
     */
    public AdoptionApply selectAdoptionApplyByApplyId(Long applyId);

    /**
     * 查询领养申请信息列表
     * 
     * @param adoptionApply 领养申请信息
     * @return 领养申请信息集合
     */
    public List<AdoptionApply> selectAdoptionApplyList(AdoptionApply adoptionApply);

    /**
     * 新增领养申请信息
     * 
     * @param adoptionApply 领养申请信息
     * @return 结果
     */
    public int insertAdoptionApply(AdoptionApply adoptionApply);

    /**
     * 修改领养申请信息
     * 
     * @param adoptionApply 领养申请信息
     * @return 结果
     */
    public int updateAdoptionApply(AdoptionApply adoptionApply);

    /**
     * 删除领养申请信息
     * 
     * @param applyId 领养申请信息主键
     * @return 结果
     */
    public int deleteAdoptionApplyByApplyId(Long applyId);

    /**
     * 批量删除领养申请信息
     * 
     * @param applyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdoptionApplyByApplyIds(Long[] applyIds);
}
