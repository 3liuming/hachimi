package com.ruoyi.hachimiSys.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.hachimiSys.mapper.PetHealthRecordMapper;
import com.ruoyi.hachimiSys.domain.PetHealthRecord;
import com.ruoyi.hachimiSys.service.IPetHealthRecordService;

/**
 * 宠物健康档案信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@Service
public class PetHealthRecordServiceImpl implements IPetHealthRecordService 
{
    @Autowired
    private PetHealthRecordMapper petHealthRecordMapper;

    /**
     * 查询宠物健康档案信息
     * 
     * @param healthId 宠物健康档案信息主键
     * @return 宠物健康档案信息
     */
    @Override
    public PetHealthRecord selectPetHealthRecordByHealthId(Long healthId)
    {
        return petHealthRecordMapper.selectPetHealthRecordByHealthId(healthId);
    }

    /**
     * 查询宠物健康档案信息列表
     * 
     * @param petHealthRecord 宠物健康档案信息
     * @return 宠物健康档案信息
     */
    @Override
    public List<PetHealthRecord> selectPetHealthRecordList(PetHealthRecord petHealthRecord)
    {
        return petHealthRecordMapper.selectPetHealthRecordList(petHealthRecord);
    }

    /**
     * 新增宠物健康档案信息
     * 
     * @param petHealthRecord 宠物健康档案信息
     * @return 结果
     */
    @Override
    public int insertPetHealthRecord(PetHealthRecord petHealthRecord)
    {
        petHealthRecord.setCreateTime(DateUtils.getNowDate());
        return petHealthRecordMapper.insertPetHealthRecord(petHealthRecord);
    }

    /**
     * 修改宠物健康档案信息
     * 
     * @param petHealthRecord 宠物健康档案信息
     * @return 结果
     */
    @Override
    public int updatePetHealthRecord(PetHealthRecord petHealthRecord)
    {
        return petHealthRecordMapper.updatePetHealthRecord(petHealthRecord);
    }

    /**
     * 批量删除宠物健康档案信息
     * 
     * @param healthIds 需要删除的宠物健康档案信息主键
     * @return 结果
     */
    @Override
    public int deletePetHealthRecordByHealthIds(Long[] healthIds)
    {
        return petHealthRecordMapper.deletePetHealthRecordByHealthIds(healthIds);
    }

    /**
     * 删除宠物健康档案信息信息
     * 
     * @param healthId 宠物健康档案信息主键
     * @return 结果
     */
    @Override
    public int deletePetHealthRecordByHealthId(Long healthId)
    {
        return petHealthRecordMapper.deletePetHealthRecordByHealthId(healthId);
    }
}
