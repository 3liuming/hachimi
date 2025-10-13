package com.ruoyi.hachimiSys.service;

import java.util.List;
import com.ruoyi.hachimiSys.domain.RescueCase;

/**
 * 救助案例信息Service接口
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public interface IRescueCaseService 
{
    /**
     * 查询救助案例信息
     * 
     * @param caseId 救助案例信息主键
     * @return 救助案例信息
     */
    public RescueCase selectRescueCaseByCaseId(Long caseId);

    /**
     * 查询救助案例信息列表
     * 
     * @param rescueCase 救助案例信息
     * @return 救助案例信息集合
     */
    public List<RescueCase> selectRescueCaseList(RescueCase rescueCase);

    /**
     * 新增救助案例信息
     * 
     * @param rescueCase 救助案例信息
     * @return 结果
     */
    public int insertRescueCase(RescueCase rescueCase);

    /**
     * 修改救助案例信息
     * 
     * @param rescueCase 救助案例信息
     * @return 结果
     */
    public int updateRescueCase(RescueCase rescueCase);

    /**
     * 批量删除救助案例信息
     * 
     * @param caseIds 需要删除的救助案例信息主键集合
     * @return 结果
     */
    public int deleteRescueCaseByCaseIds(Long[] caseIds);

    /**
     * 删除救助案例信息信息
     * 
     * @param caseId 救助案例信息主键
     * @return 结果
     */
    public int deleteRescueCaseByCaseId(Long caseId);
}
