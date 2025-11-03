package com.ruoyi.hachimi_admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hachimi_admin.mapper.CaseImageMapper;
import com.ruoyi.hachimi_admin.domain.CaseImage;
import com.ruoyi.hachimi_admin.service.ICaseImageService;

/**
 * 案例图片Service业务层处理
 * 
 * @author zhognyu
 * @date 2025-11-03
 */
@Service
public class CaseImageServiceImpl implements ICaseImageService 
{
    @Autowired
    private CaseImageMapper caseImageMapper;

    /**
     * 查询案例图片
     * 
     * @param caseImageId 案例图片主键
     * @return 案例图片
     */
    @Override
    public CaseImage selectCaseImageByCaseImageId(Long caseImageId)
    {
        return caseImageMapper.selectCaseImageByCaseImageId(caseImageId);
    }

    /**
     * 查询案例图片列表
     * 
     * @param caseImage 案例图片
     * @return 案例图片
     */
    @Override
    public List<CaseImage> selectCaseImageList(CaseImage caseImage)
    {
        return caseImageMapper.selectCaseImageList(caseImage);
    }

    /**
     * 新增案例图片
     * 
     * @param caseImage 案例图片
     * @return 结果
     */
    @Override
    public int insertCaseImage(CaseImage caseImage)
    {
        return caseImageMapper.insertCaseImage(caseImage);
    }

    /**
     * 修改案例图片
     * 
     * @param caseImage 案例图片
     * @return 结果
     */
    @Override
    public int updateCaseImage(CaseImage caseImage)
    {
        return caseImageMapper.updateCaseImage(caseImage);
    }

    /**
     * 批量删除案例图片
     * 
     * @param caseImageIds 需要删除的案例图片主键
     * @return 结果
     */
    @Override
    public int deleteCaseImageByCaseImageIds(Long[] caseImageIds)
    {
        return caseImageMapper.deleteCaseImageByCaseImageIds(caseImageIds);
    }

    /**
     * 删除案例图片信息
     * 
     * @param caseImageId 案例图片主键
     * @return 结果
     */
    @Override
    public int deleteCaseImageByCaseImageId(Long caseImageId)
    {
        return caseImageMapper.deleteCaseImageByCaseImageId(caseImageId);
    }
}
