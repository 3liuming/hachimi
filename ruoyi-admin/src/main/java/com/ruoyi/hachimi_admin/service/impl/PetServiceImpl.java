package com.ruoyi.hachimi_admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.hachimi_admin.domain.Breed;
import com.ruoyi.hachimi_admin.mapper.PetMapper;
import com.ruoyi.hachimi_admin.domain.Pet;
import com.ruoyi.hachimi_admin.service.IPetService;

/**
 * 宠物信息Service业务层处理
 * 
 * @author zhongyu
 * @date 2025-11-03
 */
@Service
public class PetServiceImpl implements IPetService 
{
    @Autowired
    private PetMapper petMapper;

    /**
     * 查询宠物信息
     * 
     * @param petId 宠物信息主键
     * @return 宠物信息
     */
    @Override
    public Pet selectPetByPetId(Long petId)
    {
        return petMapper.selectPetByPetId(petId);
    }

    /**
     * 查询宠物信息列表
     * 
     * @param pet 宠物信息
     * @return 宠物信息
     */
    @Override
    public List<Pet> selectPetList(Pet pet)
    {
        return petMapper.selectPetList(pet);
    }

    /**
     * 新增宠物信息
     * 
     * @param pet 宠物信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertPet(Pet pet)
    {
        pet.setCreateTime(DateUtils.getNowDate());
        int rows = petMapper.insertPet(pet);
        insertBreed(pet);
        return rows;
    }

    /**
     * 修改宠物信息
     * 
     * @param pet 宠物信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updatePet(Pet pet)
    {
        pet.setUpdateTime(DateUtils.getNowDate());
        petMapper.deleteBreedByBreedId(pet.getPetId());
        insertBreed(pet);
        return petMapper.updatePet(pet);
    }

    /**
     * 批量删除宠物信息
     * 
     * @param petIds 需要删除的宠物信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePetByPetIds(Long[] petIds)
    {
        petMapper.deleteBreedByBreedIds(petIds);
        return petMapper.deletePetByPetIds(petIds);
    }

    /**
     * 删除宠物信息信息
     * 
     * @param petId 宠物信息主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePetByPetId(Long petId)
    {
        petMapper.deleteBreedByBreedId(petId);
        return petMapper.deletePetByPetId(petId);
    }

    /**
     * 新增宠物品种信息
     * 
     * @param pet 宠物信息对象
     */
    public void insertBreed(Pet pet)
    {
        List<Breed> breedList = pet.getBreedList();
        Long petId = pet.getPetId();
        if (StringUtils.isNotNull(breedList))
        {
            List<Breed> list = new ArrayList<Breed>();
            for (Breed breed : breedList)
            {
                breed.setBreedId(petId);
                list.add(breed);
            }
            if (list.size() > 0)
            {
                petMapper.batchBreed(list);
            }
        }
    }
}
