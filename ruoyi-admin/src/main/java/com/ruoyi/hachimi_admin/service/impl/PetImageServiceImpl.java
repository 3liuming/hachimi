package com.ruoyi.hachimi_admin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hachimi_admin.mapper.PetImageMapper;
import com.ruoyi.hachimi_admin.domain.PetImage;
import com.ruoyi.hachimi_admin.service.IPetImageService;

/**
 * 宠物图片Service业务层处理
 * 
 * @author zhongyu
 * @date 2025-11-03
 */
@Service
public class PetImageServiceImpl implements IPetImageService 
{
    @Autowired
    private PetImageMapper petImageMapper;

    /**
     * 查询宠物图片
     * 
     * @param imageId 宠物图片主键
     * @return 宠物图片
     */
    @Override
    public PetImage selectPetImageByImageId(Long imageId)
    {
        return petImageMapper.selectPetImageByImageId(imageId);
    }

    /**
     * 查询宠物图片列表
     * 
     * @param petImage 宠物图片
     * @return 宠物图片
     */
    @Override
    public List<PetImage> selectPetImageList(PetImage petImage)
    {
        return petImageMapper.selectPetImageList(petImage);
    }

    /**
     * 新增宠物图片
     * 
     * @param petImage 宠物图片
     * @return 结果
     */
    @Override
    public int insertPetImage(PetImage petImage)
    {
        return petImageMapper.insertPetImage(petImage);
    }

    /**
     * 修改宠物图片
     * 
     * @param petImage 宠物图片
     * @return 结果
     */
    @Override
    public int updatePetImage(PetImage petImage)
    {
        return petImageMapper.updatePetImage(petImage);
    }

    /**
     * 批量删除宠物图片
     * 
     * @param imageIds 需要删除的宠物图片主键
     * @return 结果
     */
    @Override
    public int deletePetImageByImageIds(Long[] imageIds)
    {
        return petImageMapper.deletePetImageByImageIds(imageIds);
    }

    /**
     * 删除宠物图片信息
     * 
     * @param imageId 宠物图片主键
     * @return 结果
     */
    @Override
    public int deletePetImageByImageId(Long imageId)
    {
        return petImageMapper.deletePetImageByImageId(imageId);
    }
}
