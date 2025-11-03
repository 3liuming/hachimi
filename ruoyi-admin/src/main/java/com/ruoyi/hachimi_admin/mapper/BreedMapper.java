package com.ruoyi.hachimi_admin.mapper;

import java.util.List;
import com.ruoyi.hachimi_admin.domain.Breed;

/**
 * 宠物品种Mapper接口
 * 
 * @author zhongyu
 * @date 2025-11-03
 */
public interface BreedMapper 
{
    /**
     * 查询宠物品种
     * 
     * @param breedId 宠物品种主键
     * @return 宠物品种
     */
    public Breed selectBreedByBreedId(Long breedId);

    /**
     * 查询宠物品种列表
     * 
     * @param breed 宠物品种
     * @return 宠物品种集合
     */
    public List<Breed> selectBreedList(Breed breed);

    /**
     * 新增宠物品种
     * 
     * @param breed 宠物品种
     * @return 结果
     */
    public int insertBreed(Breed breed);

    /**
     * 修改宠物品种
     * 
     * @param breed 宠物品种
     * @return 结果
     */
    public int updateBreed(Breed breed);

    /**
     * 删除宠物品种
     * 
     * @param breedId 宠物品种主键
     * @return 结果
     */
    public int deleteBreedByBreedId(Long breedId);

    /**
     * 批量删除宠物品种
     * 
     * @param breedIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBreedByBreedIds(Long[] breedIds);
}
