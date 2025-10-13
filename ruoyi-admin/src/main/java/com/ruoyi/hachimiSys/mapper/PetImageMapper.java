package com.ruoyi.hachimiSys.mapper;

import java.util.List;
import com.ruoyi.hachimiSys.domain.PetImage;

/**
 * 宠物图片信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public interface PetImageMapper 
{
    /**
     * 查询宠物图片信息
     * 
     * @param imageId 宠物图片信息主键
     * @return 宠物图片信息
     */
    public PetImage selectPetImageByImageId(Long imageId);

    /**
     * 查询宠物图片信息列表
     * 
     * @param petImage 宠物图片信息
     * @return 宠物图片信息集合
     */
    public List<PetImage> selectPetImageList(PetImage petImage);

    /**
     * 新增宠物图片信息
     * 
     * @param petImage 宠物图片信息
     * @return 结果
     */
    public int insertPetImage(PetImage petImage);

    /**
     * 修改宠物图片信息
     * 
     * @param petImage 宠物图片信息
     * @return 结果
     */
    public int updatePetImage(PetImage petImage);

    /**
     * 删除宠物图片信息
     * 
     * @param imageId 宠物图片信息主键
     * @return 结果
     */
    public int deletePetImageByImageId(Long imageId);

    /**
     * 批量删除宠物图片信息
     * 
     * @param imageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePetImageByImageIds(Long[] imageIds);
}
