package com.ruoyi.hachimiCli.service.impl;

import com.ruoyi.hachimiCli.domaindto.PetAndImgDto;
import com.ruoyi.hachimiCli.mapper.PetAndImageMapper_Cli;
import com.ruoyi.hachimiCli.service.PetAndImageService_Cli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PetAndImageServiceImpl_Cli implements PetAndImageService_Cli {

    @Autowired
    private PetAndImageMapper_Cli petMapper;

    /**
     * 查询宠物列表
     */
    @Override
    public List<PetAndImgDto> selectPetList(PetAndImgDto petAndImgDto) {
        return petMapper.selectPetList(petAndImgDto);
    }

    /**
     * 根据宠物ID查询详情
     */
    @Override
    public PetAndImgDto selectPetById(Long petId) {
        return petMapper.selectPetById(petId);
    }

    /**
     * 新增宠物信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertPet(PetAndImgDto petAndImgDto) {
        return petMapper.insertPet(petAndImgDto);
    }

    /**
     * 修改宠物信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatePet(PetAndImgDto petAndImgDto) {
        return petMapper.updatePet(petAndImgDto);
    }

    /**
     * 逻辑删除宠物信息（设置 hidden = '1'）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deletePetById(Long petId) {
        // 逻辑删除宠物图片
        petMapper.deletePetImagesByPetId(petId);
        // 逻辑删除宠物信息
        return petMapper.deletePetById(petId);
    }

    /**
     * 批量逻辑删除宠物信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deletePetByIds(Long[] petIds) {
        // 逻辑删除所有宠物的图片
        for (Long petId : petIds) {
            petMapper.deletePetImagesByPetId(petId);
        }
        // 逻辑删除宠物信息
        return petMapper.deletePetByIds(petIds);
    }

    /**
     * 新增宠物图片
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertPetImage(PetAndImgDto petAndImgDto) {
        return petMapper.insertPetImage(petAndImgDto);
    }

    /**
     * 逻辑删除宠物的所有图片
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deletePetImagesByPetId(Long petId) {
        return petMapper.deletePetImagesByPetId(petId);
    }

    /**
     * 根据宠物ID查询所有图片
     */
    @Override
    public List<PetAndImgDto> selectPetImagesByPetId(Long petId) {
        return petMapper.selectPetImagesByPetId(petId);
    }

    /**
     * 新增健康记录
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertHealthRecord(PetAndImgDto petAndImgDto) {
        return petMapper.insertHealthRecord(petAndImgDto);
    }

    /**
     * 修改健康记录
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateHealthRecord(PetAndImgDto petAndImgDto) {
        return petMapper.updateHealthRecord(petAndImgDto);
    }

    /**
     * 逻辑删除健康记录
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteHealthRecordById(Long healthId) {
        return petMapper.deleteHealthRecordById(healthId);
    }

    /**
     * 根据宠物ID查询健康记录列表
     */
    @Override
    public List<PetAndImgDto> selectHealthRecordsByPetId(Long petId) {
        return petMapper.selectHealthRecordsByPetId(petId);
    }

    /**
     * 根据宠物ID查询领养申请ID
     */
    @Override
    public Long selectApplyIdByPetId(Long petId) {
        return petMapper.selectApplyIdByPetId(petId);
    }
}
