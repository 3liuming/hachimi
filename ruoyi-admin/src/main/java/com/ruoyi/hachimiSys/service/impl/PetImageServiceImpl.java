package com.ruoyi.hachimiSys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hachimiSys.mapper.PetImageMapper;
import com.ruoyi.hachimiSys.domain.PetImage;
import com.ruoyi.hachimiSys.service.IPetImageService;

/**
 * 宠物图片信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@Service
public class PetImageServiceImpl implements IPetImageService 
{
    @Autowired
    private PetImageMapper petImageMapper;

    /**
     * 查询宠物图片信息
     * 
     * @param imageId 宠物图片信息主键
     * @return 宠物图片信息
     */
    @Override
    public PetImage selectPetImageByImageId(Long imageId)
    {
        return petImageMapper.selectPetImageByImageId(imageId);
    }

    /**
     * 查询宠物图片信息列表
     * 
     * @param petImage 宠物图片信息
     * @return 宠物图片信息
     */
    @Override
    public List<PetImage> selectPetImageList(PetImage petImage)
    {
        return petImageMapper.selectPetImageList(petImage);
    }

    /**
     * 新增宠物图片信息
     * 
     * @param petImage 宠物图片信息
     * @return 结果
     */
    @Override
    public int insertPetImage(PetImage petImage)
    {
        return petImageMapper.insertPetImage(petImage);
    }

    /**
     * 修改宠物图片信息
     * 
     * @param petImage 宠物图片信息
     * @return 结果
     */
    @Override
    public int updatePetImage(PetImage petImage)
    {
        return petImageMapper.updatePetImage(petImage);
    }

    /**
     * 批量删除宠物图片信息
     * 
     * @param imageIds 需要删除的宠物图片信息主键
     * @return 结果
     */
    @Override
    public int deletePetImageByImageIds(Long[] imageIds)
    {
        return petImageMapper.deletePetImageByImageIds(imageIds);
    }

    /**
     * 删除宠物图片信息信息
     * 
     * @param imageId 宠物图片信息主键
     * @return 结果
     */
    @Override
    public int deletePetImageByImageId(Long imageId)
    {
        return petImageMapper.deletePetImageByImageId(imageId);
    }
}
