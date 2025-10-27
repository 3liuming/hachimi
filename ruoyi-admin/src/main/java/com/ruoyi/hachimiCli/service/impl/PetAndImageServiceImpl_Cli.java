package com.ruoyi.hachimiCli.service.impl;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.hachimiCli.domaindto.PetAndImgDto;
import com.ruoyi.hachimiCli.mapper.AdoptionApplyMapper_Cli;
import com.ruoyi.hachimiCli.mapper.PetAndImageMapper_Cli;
import com.ruoyi.hachimiCli.service.PetAndImageService_Cli;
import com.ruoyi.hachimiSys.domain.AdoptionApply;
import com.ruoyi.hachimiSys.mapper.AdoptionApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class PetAndImageServiceImpl_Cli implements PetAndImageService_Cli {

    @Autowired
    private PetAndImageMapper_Cli petMapper;

    @Autowired
    private AdoptionApplyMapper_Cli adoptionApplyMapper;

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
    public Long selectApplyIdByPetId(Long petId,Long userId) {
        return petMapper.selectApplyIdByPetId(petId,userId);
    }

    /**
     * 根据宠物ID和用户ID提交领养申请
     *
     * @param adoptionApply 领养申请信息
     * @param userId 当前登录用户ID
     * @return 申请ID
     */
    @Override
    @Transactional
    public Long inputApplyByPetIdAndUserId(AdoptionApply adoptionApply, Long userId)
    {
        // 1. 参数校验
        if (adoptionApply.getPetId() == null) {
            throw new ServiceException("宠物ID不能为空");
        }
        if (userId == null) {
            throw new ServiceException("用户未登录");
        }
        if (adoptionApply.getApplyReason() == null || adoptionApply.getApplyReason().trim().isEmpty()) {
            throw new ServiceException("领养理由不能为空");
        }

        // 2. 检查是否已经申请过该宠物（可选：根据业务需求判断是否允许重复申请）
        int count = adoptionApplyMapper.checkExistByPetIdAndUserId(adoptionApply.getPetId(), userId);
        if (count > 0) {
            throw new ServiceException("您已经申请过该宠物，请勿重复申请");
        }

        // 3. 设置申请信息
        adoptionApply.setUserId(userId);
        adoptionApply.setApplyTime(new Date());
        adoptionApply.setAuditStatus(0L); // 0-待审核
        adoptionApply.setHidden(0L); // 0-不隐藏

        // 清空审核相关字段（防止前端传入）
        adoptionApply.setAuditUserId(null);
        adoptionApply.setAuditRemark(null);
        adoptionApply.setAuditTime(null);
        adoptionApply.setAdoptTime(null);

        // 4. 插入数据库
        int result = adoptionApplyMapper.insertAdoptionApply(adoptionApply);

        if (result <= 0) {
            throw new ServiceException("提交申请失败");
        }

        // 5. 返回申请ID
        return adoptionApply.getApplyId();
    }
}
