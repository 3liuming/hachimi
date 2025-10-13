package com.ruoyi.hachimiSys.mapper;

import java.util.List;
import com.ruoyi.hachimiSys.domain.PetHealthRecord;

/**
 * 宠物健康档案信息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public interface PetHealthRecordMapper 
{
    /**
     * 查询宠物健康档案信息
     * 
     * @param healthId 宠物健康档案信息主键
     * @return 宠物健康档案信息
     */
    public PetHealthRecord selectPetHealthRecordByHealthId(Long healthId);

    /**
     * 查询宠物健康档案信息列表
     * 
     * @param petHealthRecord 宠物健康档案信息
     * @return 宠物健康档案信息集合
     */
    public List<PetHealthRecord> selectPetHealthRecordList(PetHealthRecord petHealthRecord);

    /**
     * 新增宠物健康档案信息
     * 
     * @param petHealthRecord 宠物健康档案信息
     * @return 结果
     */
    public int insertPetHealthRecord(PetHealthRecord petHealthRecord);

    /**
     * 修改宠物健康档案信息
     * 
     * @param petHealthRecord 宠物健康档案信息
     * @return 结果
     */
    public int updatePetHealthRecord(PetHealthRecord petHealthRecord);

    /**
     * 删除宠物健康档案信息
     * 
     * @param healthId 宠物健康档案信息主键
     * @return 结果
     */
    public int deletePetHealthRecordByHealthId(Long healthId);

    /**
     * 批量删除宠物健康档案信息
     * 
     * @param healthIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deletePetHealthRecordByHealthIds(Long[] healthIds);
}
