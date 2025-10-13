package com.ruoyi.hachimiSys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hachimiSys.mapper.RescueCaseMapper;
import com.ruoyi.hachimiSys.domain.RescueCase;
import com.ruoyi.hachimiSys.service.IRescueCaseService;

/**
 * 救助案例信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-10-10
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
    @Override
    public int insertRescueCase(RescueCase rescueCase)
    {
        return rescueCaseMapper.insertRescueCase(rescueCase);
    }

    /**
     * 修改救助案例信息
     * 
     * @param rescueCase 救助案例信息
     * @return 结果
     */
    @Override
    public int updateRescueCase(RescueCase rescueCase)
    {
        return rescueCaseMapper.updateRescueCase(rescueCase);
    }

    /**
     * 批量删除救助案例信息
     * 
     * @param caseIds 需要删除的救助案例信息主键
     * @return 结果
     */
    @Override
    public int deleteRescueCaseByCaseIds(Long[] caseIds)
    {
        return rescueCaseMapper.deleteRescueCaseByCaseIds(caseIds);
    }

    /**
     * 删除救助案例信息信息
     * 
     * @param caseId 救助案例信息主键
     * @return 结果
     */
    @Override
    public int deleteRescueCaseByCaseId(Long caseId)
    {
        return rescueCaseMapper.deleteRescueCaseByCaseId(caseId);
    }
}
