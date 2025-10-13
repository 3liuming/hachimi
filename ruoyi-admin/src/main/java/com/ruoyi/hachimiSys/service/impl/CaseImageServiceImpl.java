package com.ruoyi.hachimiSys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hachimiSys.mapper.CaseImageMapper;
import com.ruoyi.hachimiSys.domain.CaseImage;
import com.ruoyi.hachimiSys.service.ICaseImageService;

/**
 * 案例图片信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@Service
public class CaseImageServiceImpl implements ICaseImageService 
{
    @Autowired
    private CaseImageMapper caseImageMapper;

    /**
     * 查询案例图片信息
     * 
     * @param caseImageId 案例图片信息主键
     * @return 案例图片信息
     */
    @Override
    public CaseImage selectCaseImageByCaseImageId(Long caseImageId)
    {
        return caseImageMapper.selectCaseImageByCaseImageId(caseImageId);
    }

    /**
     * 查询案例图片信息列表
     * 
     * @param caseImage 案例图片信息
     * @return 案例图片信息
     */
    @Override
    public List<CaseImage> selectCaseImageList(CaseImage caseImage)
    {
        return caseImageMapper.selectCaseImageList(caseImage);
    }

    /**
     * 新增案例图片信息
     * 
     * @param caseImage 案例图片信息
     * @return 结果
     */
    @Override
    public int insertCaseImage(CaseImage caseImage)
    {
        return caseImageMapper.insertCaseImage(caseImage);
    }

    /**
     * 修改案例图片信息
     * 
     * @param caseImage 案例图片信息
     * @return 结果
     */
    @Override
    public int updateCaseImage(CaseImage caseImage)
    {
        return caseImageMapper.updateCaseImage(caseImage);
    }

    /**
     * 批量删除案例图片信息
     * 
     * @param caseImageIds 需要删除的案例图片信息主键
     * @return 结果
     */
    @Override
    public int deleteCaseImageByCaseImageIds(Long[] caseImageIds)
    {
        return caseImageMapper.deleteCaseImageByCaseImageIds(caseImageIds);
    }

    /**
     * 删除案例图片信息信息
     * 
     * @param caseImageId 案例图片信息主键
     * @return 结果
     */
    @Override
    public int deleteCaseImageByCaseImageId(Long caseImageId)
    {
        return caseImageMapper.deleteCaseImageByCaseImageId(caseImageId);
    }
}
