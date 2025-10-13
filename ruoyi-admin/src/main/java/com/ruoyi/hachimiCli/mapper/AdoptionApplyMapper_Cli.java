package com.ruoyi.hachimiCli.mapper;

import com.ruoyi.hachimiSys.domain.AdoptionApply;
import org.apache.ibatis.annotations.Update;

public interface AdoptionApplyMapper_Cli {

    @Update("UPDATE adoption_apply SET apply_reason = #{applyReason} WHERE apply_id = #{applyId}")
    int updateAdoptionApplyByApplyId(AdoptionApply adoptionApply);

    @Update("UPDATE adoption_apply SET hidden = '1' WHERE apply_id = #{applyId}")
    int deleteAdoptionApplyByApplyId(Long applyId);
}
