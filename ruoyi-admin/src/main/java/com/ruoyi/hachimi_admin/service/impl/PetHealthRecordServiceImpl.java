package com.ruoyi.hachimi_admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.hachimi_admin.domain.Pet;
import com.ruoyi.hachimi_admin.mapper.PetHealthRecordMapper;
import com.ruoyi.hachimi_admin.domain.PetHealthRecord;
import com.ruoyi.hachimi_admin.service.IPetHealthRecordService;

/**
 * 宠物健康档案Service业务层处理
 * 
 * @author zhognyu
 * @date 2025-11-03
 */
@Service
public class PetHealthRecordServiceImpl implements IPetHealthRecordService 
{
    @Autowired
    private PetHealthRecordMapper petHealthRecordMapper;

    /**
     * 查询宠物健康档案
     * 
     * @param healthId 宠物健康档案主键
     * @return 宠物健康档案
     */
    @Override
    public PetHealthRecord selectPetHealthRecordByHealthId(Long healthId)
    {
        return petHealthRecordMapper.selectPetHealthRecordByHealthId(healthId);
    }

    /**
     * 查询宠物健康档案列表
     * 
     * @param petHealthRecord 宠物健康档案
     * @return 宠物健康档案
     */
    @Override
    public List<PetHealthRecord> selectPetHealthRecordList(PetHealthRecord petHealthRecord)
    {
        return petHealthRecordMapper.selectPetHealthRecordList(petHealthRecord);
    }

    /**
     * 新增宠物健康档案
     * 
     * @param petHealthRecord 宠物健康档案
     * @return 结果
     */
    @Transactional
    @Override
    public int insertPetHealthRecord(PetHealthRecord petHealthRecord)
    {
        petHealthRecord.setCreateTime(DateUtils.getNowDate());
        int rows = petHealthRecordMapper.insertPetHealthRecord(petHealthRecord);
        insertPet(petHealthRecord);
        return rows;
    }

    /**
     * 修改宠物健康档案
     * 
     * @param petHealthRecord 宠物健康档案
     * @return 结果
     */
    @Transactional
    @Override
    public int updatePetHealthRecord(PetHealthRecord petHealthRecord)
    {
        petHealthRecordMapper.deletePetByPetId(petHealthRecord.getHealthId());
        insertPet(petHealthRecord);
        return petHealthRecordMapper.updatePetHealthRecord(petHealthRecord);
    }

    /**
     * 批量删除宠物健康档案
     * 
     * @param healthIds 需要删除的宠物健康档案主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePetHealthRecordByHealthIds(Long[] healthIds)
    {
        petHealthRecordMapper.deletePetByPetIds(healthIds);
        return petHealthRecordMapper.deletePetHealthRecordByHealthIds(healthIds);
    }

    /**
     * 删除宠物健康档案信息
     * 
     * @param healthId 宠物健康档案主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePetHealthRecordByHealthId(Long healthId)
    {
        petHealthRecordMapper.deletePetByPetId(healthId);
        return petHealthRecordMapper.deletePetHealthRecordByHealthId(healthId);
    }

    /**
     * 新增宠物信息信息
     * 
     * @param petHealthRecord 宠物健康档案对象
     */
    public void insertPet(PetHealthRecord petHealthRecord)
    {
        List<Pet> petList = petHealthRecord.getPetList();
        Long healthId = petHealthRecord.getHealthId();
        if (StringUtils.isNotNull(petList))
        {
            List<Pet> list = new ArrayList<Pet>();
            for (Pet pet : petList)
            {
                pet.setPetId(healthId);
                list.add(pet);
            }
            if (list.size() > 0)
            {
                petHealthRecordMapper.batchPet(list);
            }
        }
    }
}
