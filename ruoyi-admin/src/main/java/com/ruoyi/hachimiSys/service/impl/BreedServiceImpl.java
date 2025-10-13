package com.ruoyi.hachimiSys.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hachimiSys.mapper.BreedMapper;
import com.ruoyi.hachimiSys.domain.Breed;
import com.ruoyi.hachimiSys.service.IBreedService;

/**
 * 宠物品种信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@Service
public class BreedServiceImpl implements IBreedService 
{
    @Autowired
    private BreedMapper breedMapper;

    /**
     * 查询宠物品种信息
     * 
     * @param breedId 宠物品种信息主键
     * @return 宠物品种信息
     */
    @Override
    public Breed selectBreedByBreedId(Long breedId)
    {
        return breedMapper.selectBreedByBreedId(breedId);
    }

    /**
     * 查询宠物品种信息列表
     * 
     * @param breed 宠物品种信息
     * @return 宠物品种信息
     */
    @Override
    public List<Breed> selectBreedList(Breed breed)
    {
        return breedMapper.selectBreedList(breed);
    }

    /**
     * 新增宠物品种信息
     * 
     * @param breed 宠物品种信息
     * @return 结果
     */
    @Override
    public int insertBreed(Breed breed)
    {
        breed.setCreateTime(DateUtils.getNowDate());
        return breedMapper.insertBreed(breed);
    }

    /**
     * 修改宠物品种信息
     * 
     * @param breed 宠物品种信息
     * @return 结果
     */
    @Override
    public int updateBreed(Breed breed)
    {
        return breedMapper.updateBreed(breed);
    }

    /**
     * 批量删除宠物品种信息
     * 
     * @param breedIds 需要删除的宠物品种信息主键
     * @return 结果
     */
    @Override
    public int deleteBreedByBreedIds(Long[] breedIds)
    {
        return breedMapper.deleteBreedByBreedIds(breedIds);
    }

    /**
     * 删除宠物品种信息信息
     * 
     * @param breedId 宠物品种信息主键
     * @return 结果
     */
    @Override
    public int deleteBreedByBreedId(Long breedId)
    {
        return breedMapper.deleteBreedByBreedId(breedId);
    }
}
