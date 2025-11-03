package com.ruoyi.hachimi_admin.mapper;

import java.util.List;
import com.ruoyi.hachimi_admin.domain.PetHealthRecord;
import com.ruoyi.hachimi_admin.domain.Pet;

/**
 * 宠物健康档案Mapper接口
 * 
 * @author zhognyu
 * @date 2025-11-03
 */
public interface PetHealthRecordMapper 
{
    /**
     * 查询宠物健康档案
     * 
     * @param healthId 宠物健康档案主键
     * @return 宠物健康档案
     */
    public PetHealthRecord selectPetHealthRecordByHealthId(Long healthId);

    /**
     * 查询宠物健康档案列表
     * 
     * @param petHealthRecord 宠物健康档案
     * @return 宠物健康档案集合
     */
    public List<PetHealthRecord> selectPetHealthRecordList(PetHealthRecord petHealthRecord);

    /**
     * 新增宠物健康档案
     * 
     * @param petHealthRecord 宠物健康档案
     * @return 结果
     */
    public int insertPetHealthRecord(PetHealthRecord petHealthRecord);

    /**
     * 修改宠物健康档案
     * 
     * @param petHealthRecord 宠物健康档案
     * @return 结果
     */
    public int updatePetHealthRecord(PetHealthRecord petHealthRecord);

    /**
     * 删除宠物健康档案
     * 
     * @param healthId 宠物健康档案主键
     * @return 结果
     */
    public int deletePetHealthRecordByHealthId(Long healthId);

    /**
     * 批量删除宠物健康档案
     * 
     * @param healthIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePetHealthRecordByHealthIds(Long[] healthIds);

    /**
     * 批量删除宠物信息
     * 
     * @param healthIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePetByPetIds(Long[] healthIds);
    
    /**
     * 批量新增宠物信息
     * 
     * @param petList 宠物信息列表
     * @return 结果
     */
    public int batchPet(List<Pet> petList);
    

    /**
     * 通过宠物健康档案主键删除宠物信息信息
     * 
     * @param healthId 宠物健康档案ID
     * @return 结果
     */
    public int deletePetByPetId(Long healthId);
}
