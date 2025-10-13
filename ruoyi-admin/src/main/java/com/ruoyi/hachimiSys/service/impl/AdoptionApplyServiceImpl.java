package com.ruoyi.hachimiSys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hachimiSys.mapper.AdoptionApplyMapper;
import com.ruoyi.hachimiSys.domain.AdoptionApply;
import com.ruoyi.hachimiSys.service.IAdoptionApplyService;

/**
 * 领养申请信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@Service
public class AdoptionApplyServiceImpl implements IAdoptionApplyService 
{
    @Autowired
    private AdoptionApplyMapper adoptionApplyMapper;

    /**
     * 查询领养申请信息
     * 
     * @param applyId 领养申请信息主键
     * @return 领养申请信息
     */
    @Override
    public AdoptionApply selectAdoptionApplyByApplyId(Long applyId)
    {
        return adoptionApplyMapper.selectAdoptionApplyByApplyId(applyId);
    }

    /**
     * 查询领养申请信息列表
     * 
     * @param adoptionApply 领养申请信息
     * @return 领养申请信息
     */
    @Override
    public List<AdoptionApply> selectAdoptionApplyList(AdoptionApply adoptionApply)
    {
        return adoptionApplyMapper.selectAdoptionApplyList(adoptionApply);
    }

    /**
     * 新增领养申请信息
     * 
     * @param adoptionApply 领养申请信息
     * @return 结果
     */
    @Override
    public int insertAdoptionApply(AdoptionApply adoptionApply)
    {
        return adoptionApplyMapper.insertAdoptionApply(adoptionApply);
    }

    /**
     * 修改领养申请信息
     * 
     * @param adoptionApply 领养申请信息
     * @return 结果
     */
    @Override
    public int updateAdoptionApply(AdoptionApply adoptionApply)
    {
        return adoptionApplyMapper.updateAdoptionApply(adoptionApply);
    }

    /**
     * 批量删除领养申请信息
     * 
     * @param applyIds 需要删除的领养申请信息主键
     * @return 结果
     */
    @Override
    public int deleteAdoptionApplyByApplyIds(Long[] applyIds)
    {
        return adoptionApplyMapper.deleteAdoptionApplyByApplyIds(applyIds);
    }

    /**
     * 删除领养申请信息信息
     * 
     * @param applyId 领养申请信息主键
     * @return 结果
     */
    @Override
    public int deleteAdoptionApplyByApplyId(Long applyId)
    {
        return adoptionApplyMapper.deleteAdoptionApplyByApplyId(applyId);
    }
}
