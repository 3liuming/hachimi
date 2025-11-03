package com.ruoyi.hachimi_admin.mapper;

import java.util.List;
import com.ruoyi.hachimi_admin.domain.PetImage;

/**
 * 宠物图片Mapper接口
 * 
 * @author zhongyu
 * @date 2025-11-03
 */
public interface PetImageMapper 
{
    /**
     * 查询宠物图片
     * 
     * @param imageId 宠物图片主键
     * @return 宠物图片
     */
    public PetImage selectPetImageByImageId(Long imageId);

    /**
     * 查询宠物图片列表
     * 
     * @param petImage 宠物图片
     * @return 宠物图片集合
     */
    public List<PetImage> selectPetImageList(PetImage petImage);

    /**
     * 新增宠物图片
     * 
     * @param petImage 宠物图片
     * @return 结果
     */
    public int insertPetImage(PetImage petImage);

    /**
     * 修改宠物图片
     * 
     * @param petImage 宠物图片
     * @return 结果
     */
    public int updatePetImage(PetImage petImage);

    /**
     * 删除宠物图片
     * 
     * @param imageId 宠物图片主键
     * @return 结果
     */
    public int deletePetImageByImageId(Long imageId);

    /**
     * 批量删除宠物图片
     * 
     * @param imageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePetImageByImageIds(Long[] imageIds);
}
