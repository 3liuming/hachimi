package com.ruoyi.hachimiCli.mapper;

import com.ruoyi.hachimi_admin.domain.AdoptionApply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AdoptionApplyMapper_Cli {

    @Update("UPDATE adoption_apply SET apply_reason = #{applyReason} WHERE apply_id = #{applyId}")
    int updateAdoptionApplyByApplyId(AdoptionApply adoptionApply);

    @Update("UPDATE adoption_apply SET hidden = '1' WHERE apply_id = #{applyId}")
    int deleteByUpdateAdoptionApplyByApplyId(Long applyId);

    /**
     * 查询领养申请信息
     *
     * @param applyId 领养申请信息主键
     * @return 领养申请信息
     */
    public AdoptionApply selectAdoptionApplyByApplyId(Long applyId);

    /**
     * 查询领养申请信息列表
     *
     * @param adoptionApply 领养申请信息
     * @return 领养申请信息集合
     */
    public List<AdoptionApply> selectAdoptionApplyList(AdoptionApply adoptionApply);

    /**
     * 新增领养申请信息
     *
     * @param adoptionApply 领养申请信息
     * @return 结果
     */
    public int insertAdoptionApply(AdoptionApply adoptionApply);

    /**
     * 修改领养申请信息
     *
     * @param adoptionApply 领养申请信息
     * @return 结果
     */
    public int updateAdoptionApply(AdoptionApply adoptionApply);

    /**
     * 删除领养申请信息
     *
     * @param applyId 领养申请信息主键
     * @return 结果
     */
    public int deleteAdoptionApplyByApplyId(Long applyId);

    /**
     * 批量删除领养申请信息
     *
     * @param applyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAdoptionApplyByApplyIds(Long[] applyIds);

    /**
     * 根据宠物ID和用户ID查询领养申请信息
     *
     * @param petId 宠物ID
     * @param userId 用户ID
     * @return 领养申请信息
     */
    public AdoptionApply selectAdoptionApplyByPetIdAndUserId(@Param("petId") Long petId, @Param("userId") Long userId);

    /**
     * 检查用户是否已经申请过该宠物
     *
     * @param petId 宠物ID
     * @param userId 用户ID
     * @return 申请数量
     */
    public int checkExistByPetIdAndUserId(@Param("petId") Long petId, @Param("userId") Long userId);
}
