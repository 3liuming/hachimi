package com.ruoyi.hachimi_admin.mapper;

import java.util.List;
import com.ruoyi.hachimi_admin.domain.CaseImage;

/**
 * 案例图片Mapper接口
 * 
 * @author zhognyu
 * @date 2025-11-03
 */
public interface CaseImageMapper 
{
    /**
     * 查询案例图片
     * 
     * @param caseImageId 案例图片主键
     * @return 案例图片
     */
    public CaseImage selectCaseImageByCaseImageId(Long caseImageId);

    /**
     * 查询案例图片列表
     * 
     * @param caseImage 案例图片
     * @return 案例图片集合
     */
    public List<CaseImage> selectCaseImageList(CaseImage caseImage);

    /**
     * 新增案例图片
     * 
     * @param caseImage 案例图片
     * @return 结果
     */
    public int insertCaseImage(CaseImage caseImage);

    /**
     * 修改案例图片
     * 
     * @param caseImage 案例图片
     * @return 结果
     */
    public int updateCaseImage(CaseImage caseImage);

    /**
     * 删除案例图片
     * 
     * @param caseImageId 案例图片主键
     * @return 结果
     */
    public int deleteCaseImageByCaseImageId(Long caseImageId);

    /**
     * 批量删除案例图片
     * 
     * @param caseImageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCaseImageByCaseImageIds(Long[] caseImageIds);
}
