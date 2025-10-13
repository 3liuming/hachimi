package com.ruoyi.hachimiSys.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hachimiSys.mapper.PetMapper;
import com.ruoyi.hachimiSys.domain.Pet;
import com.ruoyi.hachimiSys.service.IPetService;

/**
 * 宠物信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-10-10
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
    @Override
    public int insertPet(Pet pet)
    {
        pet.setCreateTime(DateUtils.getNowDate());
        return petMapper.insertPet(pet);
    }

    /**
     * 修改宠物信息
     * 
     * @param pet 宠物信息
     * @return 结果
     */
    @Override
    public int updatePet(Pet pet)
    {
        pet.setUpdateTime(DateUtils.getNowDate());
        return petMapper.updatePet(pet);
    }

    /**
     * 批量删除宠物信息
     * 
     * @param petIds 需要删除的宠物信息主键
     * @return 结果
     */
    @Override
    public int deletePetByPetIds(Long[] petIds)
    {
        return petMapper.deletePetByPetIds(petIds);
    }

    /**
     * 删除宠物信息信息
     * 
     * @param petId 宠物信息主键
     * @return 结果
     */
    @Override
    public int deletePetByPetId(Long petId)
    {
        return petMapper.deletePetByPetId(petId);
    }
}
