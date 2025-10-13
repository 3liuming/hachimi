package com.ruoyi.hachimiCli.service;

import com.ruoyi.hachimiCli.domaindto.PetAndImgDto;

import java.util.List;

public interface PetAndImageService_Cli {
    /**
     * 查询宠物列表
     */
    List<PetAndImgDto> selectPetList(PetAndImgDto petAndImgDto);

    /**
     * 根据宠物ID查询详情
     */
    PetAndImgDto selectPetById(Long petId);

    /**
     * 新增宠物信息
     */
    int insertPet(PetAndImgDto petAndImgDto);

    /**
     * 修改宠物信息
     */
    int updatePet(PetAndImgDto petAndImgDto);

    /**
     * 逻辑删除宠物信息
     */
    int deletePetById(Long petId);

    /**
     * 批量逻辑删除宠物信息
     */
    int deletePetByIds(Long[] petIds);

    /**
     * 新增宠物图片
     */
    int insertPetImage(PetAndImgDto petAndImgDto);

    /**
     * 逻辑删除宠物的所有图片
     */
    int deletePetImagesByPetId(Long petId);

    /**
     * 根据宠物ID查询所有图片
     */
    List<PetAndImgDto> selectPetImagesByPetId(Long petId);

    /**
     * 新增健康记录
     */
    int insertHealthRecord(PetAndImgDto petAndImgDto);

    /**
     * 修改健康记录
     */
    int updateHealthRecord(PetAndImgDto petAndImgDto);

    /**
     * 逻辑删除健康记录
     */
    int deleteHealthRecordById(Long healthId);

    /**
     * 根据宠物ID查询健康记录列表
     */
    List<PetAndImgDto> selectHealthRecordsByPetId(Long petId);

    /**
     * 根据宠物ID查询领养申请ID
     */
    Long selectApplyIdByPetId(Long petId);
}
