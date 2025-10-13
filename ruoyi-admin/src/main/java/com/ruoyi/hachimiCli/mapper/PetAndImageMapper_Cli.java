package com.ruoyi.hachimiCli.mapper;

import com.ruoyi.hachimiCli.domaindto.PetAndImgDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PetAndImageMapper_Cli {



    /**
     * 查询宠物列表（带图片、品种和健康记录信息）
     */
    List<PetAndImgDto> selectPetList(PetAndImgDto petAndImgDto);

    /**
     * 根据宠物ID查询详情（带图片、品种和健康记录信息）
     */
    PetAndImgDto selectPetById(@Param("petId") Long petId);

    /**
     * 新增宠物基本信息
     */
    int insertPet(PetAndImgDto petAndImgDto);

    /**
     * 修改宠物基本信息
     */
    int updatePet(PetAndImgDto petAndImgDto);

    /**
     * 逻辑删除宠物信息（设置 hidden = '1'）
     */
    int deletePetById(@Param("petId") Long petId);

    /**
     * 批量逻辑删除宠物信息
     */
    int deletePetByIds(@Param("petIds") Long[] petIds);

    /**
     * 新增宠物图片
     */
    int insertPetImage(PetAndImgDto petAndImgDto);

    /**
     * 逻辑删除宠物的所有图片
     */
    int deletePetImagesByPetId(@Param("petId") Long petId);

    /**
     * 根据宠物ID查询所有图片
     */
    List<PetAndImgDto> selectPetImagesByPetId(@Param("petId") Long petId);

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
    int deleteHealthRecordById(@Param("healthId") Long healthId);

    /**
     * 根据宠物ID查询健康记录列表
     */
    List<PetAndImgDto> selectHealthRecordsByPetId(@Param("petId") Long petId);

    /**
     * 根据宠物ID查询领养申请ID
     */
    Long selectApplyIdByPetId(@Param("petId") Long petId);

}
