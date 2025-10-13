package com.ruoyi.hachimiSys.mapper;

import java.util.List;
import com.ruoyi.hachimiSys.domain.CaseImage;

/**
 * 案例图片信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public interface CaseImageMapper 
{
    /**
     * 查询案例图片信息
     * 
     * @param caseImageId 案例图片信息主键
     * @return 案例图片信息
     */
    public CaseImage selectCaseImageByCaseImageId(Long caseImageId);

    /**
     * 查询案例图片信息列表
     * 
     * @param caseImage 案例图片信息
     * @return 案例图片信息集合
     */
    public List<CaseImage> selectCaseImageList(CaseImage caseImage);

    /**
     * 新增案例图片信息
     * 
     * @param caseImage 案例图片信息
     * @return 结果
     */
    public int insertCaseImage(CaseImage caseImage);

    /**
     * 修改案例图片信息
     * 
     * @param caseImage 案例图片信息
     * @return 结果
     */
    public int updateCaseImage(CaseImage caseImage);

    /**
     * 删除案例图片信息
     * 
     * @param caseImageId 案例图片信息主键
     * @return 结果
     */
    public int deleteCaseImageByCaseImageId(Long caseImageId);

    /**
     * 批量删除案例图片信息
     * 
     * @param caseImageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCaseImageByCaseImageIds(Long[] caseImageIds);
}
