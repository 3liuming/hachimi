package com.ruoyi.hachimi_admin.service;

import java.util.List;
import com.ruoyi.hachimi_admin.domain.PetHealthRecord;

/**
 * 宠物健康档案Service接口
 * 
 * @author zhognyu
 * @date 2025-11-03
 */
public interface IPetHealthRecordService 
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
     * 批量删除宠物健康档案
     * 
     * @param healthIds 需要删除的宠物健康档案主键集合
     * @return 结果
     */
    public int deletePetHealthRecordByHealthIds(Long[] healthIds);

    /**
     * 删除宠物健康档案信息
     * 
     * @param healthId 宠物健康档案主键
     * @return 结果
     */
    public int deletePetHealthRecordByHealthId(Long healthId);
}
