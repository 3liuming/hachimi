package com.ruoyi.hachimi_admin.mapper;

import java.util.List;
import com.ruoyi.hachimi_admin.domain.RescueCase;
import com.ruoyi.hachimi_admin.domain.CaseImage;

/**
 * 救助案例信息Mapper接口
 * 
 * @author zhongyu
 * @date 2025-11-03
 */
public interface RescueCaseMapper 
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
     * 删除救助案例信息
     * 
     * @param caseId 救助案例信息主键
     * @return 结果
     */
    public int deleteRescueCaseByCaseId(Long caseId);

    /**
     * 批量删除救助案例信息
     * 
     * @param caseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRescueCaseByCaseIds(Long[] caseIds);

    /**
     * 批量删除案例图片
     * 
     * @param caseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCaseImageByCaseIds(Long[] caseIds);
    
    /**
     * 批量新增案例图片
     * 
     * @param caseImageList 案例图片列表
     * @return 结果
     */
    public int batchCaseImage(List<CaseImage> caseImageList);
    

    /**
     * 通过救助案例信息主键删除案例图片信息
     * 
     * @param caseId 救助案例信息ID
     * @return 结果
     */
    public int deleteCaseImageByCaseId(Long caseId);
}
