package com.ruoyi.hachimiSys.service;

import java.util.List;
import com.ruoyi.hachimiSys.domain.Breed;

/**
 * 宠物品种信息Service接口
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public interface IBreedService 
{
    /**
     * 查询宠物品种信息
     * 
     * @param breedId 宠物品种信息主键
     * @return 宠物品种信息
     */
    public Breed selectBreedByBreedId(Long breedId);

    /**
     * 查询宠物品种信息列表
     * 
     * @param breed 宠物品种信息
     * @return 宠物品种信息集合
     */
    public List<Breed> selectBreedList(Breed breed);

    /**
     * 新增宠物品种信息
     * 
     * @param breed 宠物品种信息
     * @return 结果
     */
    public int insertBreed(Breed breed);

    /**
     * 修改宠物品种信息
     * 
     * @param breed 宠物品种信息
     * @return 结果
     */
    public int updateBreed(Breed breed);

    /**
     * 批量删除宠物品种信息
     * 
     * @param breedIds 需要删除的宠物品种信息主键集合
     * @return 结果
     */
    public int deleteBreedByBreedIds(Long[] breedIds);

    /**
     * 删除宠物品种信息信息
     * 
     * @param breedId 宠物品种信息主键
     * @return 结果
     */
    public int deleteBreedByBreedId(Long breedId);
}
