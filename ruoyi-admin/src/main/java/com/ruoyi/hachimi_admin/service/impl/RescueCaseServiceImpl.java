package com.ruoyi.hachimi_admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.hachimi_admin.domain.CaseImage;
import com.ruoyi.hachimi_admin.mapper.RescueCaseMapper;
import com.ruoyi.hachimi_admin.domain.RescueCase;
import com.ruoyi.hachimi_admin.service.IRescueCaseService;

/**
 * 救助案例信息Service业务层处理
 * 
 * @author zhongyu
 * @date 2025-11-03
 */
@Service
public class RescueCaseServiceImpl implements IRescueCaseService 
{
    @Autowired
    private RescueCaseMapper rescueCaseMapper;

    /**
     * 查询救助案例信息
     * 
     * @param caseId 救助案例信息主键
     * @return 救助案例信息
     */
    @Override
    public RescueCase selectRescueCaseByCaseId(Long caseId)
    {
        return rescueCaseMapper.selectRescueCaseByCaseId(caseId);
    }

    /**
     * 查询救助案例信息列表
     * 
     * @param rescueCase 救助案例信息
     * @return 救助案例信息
     */
    @Override
    public List<RescueCase> selectRescueCaseList(RescueCase rescueCase)
    {
        return rescueCaseMapper.selectRescueCaseList(rescueCase);
    }

    /**
     * 新增救助案例信息
     * 
     * @param rescueCase 救助案例信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertRescueCase(RescueCase rescueCase)
    {
        int rows = rescueCaseMapper.insertRescueCase(rescueCase);
        insertCaseImage(rescueCase);
        return rows;
    }

    /**
     * 修改救助案例信息
     * 
     * @param rescueCase 救助案例信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateRescueCase(RescueCase rescueCase)
    {
        rescueCaseMapper.deleteCaseImageByCaseId(rescueCase.getCaseId());
        insertCaseImage(rescueCase);
        return rescueCaseMapper.updateRescueCase(rescueCase);
    }

    /**
     * 批量删除救助案例信息
     * 
     * @param caseIds 需要删除的救助案例信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteRescueCaseByCaseIds(Long[] caseIds)
    {
        rescueCaseMapper.deleteCaseImageByCaseIds(caseIds);
        return rescueCaseMapper.deleteRescueCaseByCaseIds(caseIds);
    }

    /**
     * 删除救助案例信息信息
     * 
     * @param caseId 救助案例信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteRescueCaseByCaseId(Long caseId)
    {
        rescueCaseMapper.deleteCaseImageByCaseId(caseId);
        return rescueCaseMapper.deleteRescueCaseByCaseId(caseId);
    }

    /**
     * 新增案例图片信息
     * 
     * @param rescueCase 救助案例信息对象
     */
    public void insertCaseImage(RescueCase rescueCase)
    {
        List<CaseImage> caseImageList = rescueCase.getCaseImageList();
        Long caseId = rescueCase.getCaseId();
        if (StringUtils.isNotNull(caseImageList))
        {
            List<CaseImage> list = new ArrayList<CaseImage>();
            for (CaseImage caseImage : caseImageList)
            {
                caseImage.setCaseId(caseId);
                list.add(caseImage);
            }
            if (list.size() > 0)
            {
                rescueCaseMapper.batchCaseImage(list);
            }
        }
    }
}
