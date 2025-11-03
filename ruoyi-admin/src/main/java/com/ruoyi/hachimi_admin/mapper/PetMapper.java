package com.ruoyi.hachimi_admin.mapper;

import java.util.List;
import com.ruoyi.hachimi_admin.domain.Pet;
import com.ruoyi.hachimi_admin.domain.Breed;

/**
 * 宠物信息Mapper接口
 * 
 * @author zhongyu
 * @date 2025-11-03
 */
public interface PetMapper 
{
    /**
     * 查询宠物信息
     * 
     * @param petId 宠物信息主键
     * @return 宠物信息
     */
    public Pet selectPetByPetId(Long petId);

    /**
     * 查询宠物信息列表
     * 
     * @param pet 宠物信息
     * @return 宠物信息集合
     */
    public List<Pet> selectPetList(Pet pet);

    /**
     * 新增宠物信息
     * 
     * @param pet 宠物信息
     * @return 结果
     */
    public int insertPet(Pet pet);

    /**
     * 修改宠物信息
     * 
     * @param pet 宠物信息
     * @return 结果
     */
    public int updatePet(Pet pet);

    /**
     * 删除宠物信息
     * 
     * @param petId 宠物信息主键
     * @return 结果
     */
    public int deletePetByPetId(Long petId);

    /**
     * 批量删除宠物信息
     * 
     * @param petIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePetByPetIds(Long[] petIds);

    /**
     * 批量删除宠物品种
     * 
     * @param petIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBreedByBreedIds(Long[] petIds);
    
    /**
     * 批量新增宠物品种
     * 
     * @param breedList 宠物品种列表
     * @return 结果
     */
    public int batchBreed(List<Breed> breedList);
    

    /**
     * 通过宠物信息主键删除宠物品种信息
     * 
     * @param petId 宠物信息ID
     * @return 结果
     */
    public int deleteBreedByBreedId(Long petId);
}
