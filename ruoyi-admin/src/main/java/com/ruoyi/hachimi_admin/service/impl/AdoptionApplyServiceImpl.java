package com.ruoyi.hachimi_admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.hachimi_admin.domain.AdoptFeedback;
import com.ruoyi.hachimi_admin.mapper.AdoptionApplyMapper;
import com.ruoyi.hachimi_admin.domain.AdoptionApply;
import com.ruoyi.hachimi_admin.service.IAdoptionApplyService;

/**
 * 领养申请Service业务层处理
 * 
 * @author zhongyu
 * @date 2025-11-03
 */
@Service
public class AdoptionApplyServiceImpl implements IAdoptionApplyService 
{
    @Autowired
    private AdoptionApplyMapper adoptionApplyMapper;

    /**
     * 查询领养申请
     * 
     * @param applyId 领养申请主键
     * @return 领养申请
     */
    @Override
    public AdoptionApply selectAdoptionApplyByApplyId(Long applyId)
    {
        return adoptionApplyMapper.selectAdoptionApplyByApplyId(applyId);
    }

    /**
     * 查询领养申请列表
     * 
     * @param adoptionApply 领养申请
     * @return 领养申请
     */
    @Override
    public List<AdoptionApply> selectAdoptionApplyList(AdoptionApply adoptionApply)
    {
        return adoptionApplyMapper.selectAdoptionApplyList(adoptionApply);
    }

    /**
     * 新增领养申请
     * 
     * @param adoptionApply 领养申请
     * @return 结果
     */
    @Transactional
    @Override
    public int insertAdoptionApply(AdoptionApply adoptionApply)
    {
        int rows = adoptionApplyMapper.insertAdoptionApply(adoptionApply);
        insertAdoptFeedback(adoptionApply);
        return rows;
    }

    /**
     * 修改领养申请
     * 
     * @param adoptionApply 领养申请
     * @return 结果
     */
    @Transactional
    @Override
    public int updateAdoptionApply(AdoptionApply adoptionApply)
    {
        adoptionApplyMapper.deleteAdoptFeedbackByApplyId(adoptionApply.getApplyId());
        insertAdoptFeedback(adoptionApply);
        return adoptionApplyMapper.updateAdoptionApply(adoptionApply);
    }

    /**
     * 批量删除领养申请
     * 
     * @param applyIds 需要删除的领养申请主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAdoptionApplyByApplyIds(Long[] applyIds)
    {
        adoptionApplyMapper.deleteAdoptFeedbackByApplyIds(applyIds);
        return adoptionApplyMapper.deleteAdoptionApplyByApplyIds(applyIds);
    }

    /**
     * 删除领养申请信息
     * 
     * @param applyId 领养申请主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAdoptionApplyByApplyId(Long applyId)
    {
        adoptionApplyMapper.deleteAdoptFeedbackByApplyId(applyId);
        return adoptionApplyMapper.deleteAdoptionApplyByApplyId(applyId);
    }

    /**
     * 新增领养后反馈信息
     * 
     * @param adoptionApply 领养申请对象
     */
    public void insertAdoptFeedback(AdoptionApply adoptionApply)
    {
        List<AdoptFeedback> adoptFeedbackList = adoptionApply.getAdoptFeedbackList();
        Long applyId = adoptionApply.getApplyId();
        if (StringUtils.isNotNull(adoptFeedbackList))
        {
            List<AdoptFeedback> list = new ArrayList<AdoptFeedback>();
            for (AdoptFeedback adoptFeedback : adoptFeedbackList)
            {
                adoptFeedback.setApplyId(applyId);
                list.add(adoptFeedback);
            }
            if (list.size() > 0)
            {
                adoptionApplyMapper.batchAdoptFeedback(list);
            }
        }
    }
}
