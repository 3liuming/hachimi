package com.ruoyi.hachimi_admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hachimi_admin.mapper.BreedMapper;
import com.ruoyi.hachimi_admin.domain.Breed;
import com.ruoyi.hachimi_admin.service.IBreedService;

/**
 * 宠物品种Service业务层处理
 * 
 * @author zhongyu
 * @date 2025-11-03
 */
@Service
public class BreedServiceImpl implements IBreedService 
{
    @Autowired
    private BreedMapper breedMapper;

    /**
     * 查询宠物品种
     * 
     * @param breedId 宠物品种主键
     * @return 宠物品种
     */
    @Override
    public Breed selectBreedByBreedId(Long breedId)
    {
        return breedMapper.selectBreedByBreedId(breedId);
    }

    /**
     * 查询宠物品种列表
     * 
     * @param breed 宠物品种
     * @return 宠物品种
     */
    @Override
    public List<Breed> selectBreedList(Breed breed)
    {
        return breedMapper.selectBreedList(breed);
    }

    /**
     * 新增宠物品种
     * 
     * @param breed 宠物品种
     * @return 结果
     */
    @Override
    public int insertBreed(Breed breed)
    {
        breed.setCreateTime(DateUtils.getNowDate());
        return breedMapper.insertBreed(breed);
    }

    /**
     * 修改宠物品种
     * 
     * @param breed 宠物品种
     * @return 结果
     */
    @Override
    public int updateBreed(Breed breed)
    {
        return breedMapper.updateBreed(breed);
    }

    /**
     * 批量删除宠物品种
     * 
     * @param breedIds 需要删除的宠物品种主键
     * @return 结果
     */
    @Override
    public int deleteBreedByBreedIds(Long[] breedIds)
    {
        return breedMapper.deleteBreedByBreedIds(breedIds);
    }

    /**
     * 删除宠物品种信息
     * 
     * @param breedId 宠物品种主键
     * @return 结果
     */
    @Override
    public int deleteBreedByBreedId(Long breedId)
    {
        return breedMapper.deleteBreedByBreedId(breedId);
    }
}
