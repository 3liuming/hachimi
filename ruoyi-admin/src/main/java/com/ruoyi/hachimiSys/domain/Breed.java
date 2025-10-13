package com.ruoyi.hachimiSys.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 宠物品种信息对象 breed
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
public class Breed extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 品种唯一ID */
    @Excel(name = "品种唯一ID")
    private Long breedId;

    /** 品种名称（如：金毛、布偶猫） */
    @Excel(name = "品种名称", readConverterExp = "如=：金毛、布偶猫")
    private String breedName;

    /** 所属物种（如：狗、猫） */
    @Excel(name = "所属物种", readConverterExp = "如：狗、猫")
    private String species;

    /** 是否隐藏：0-不隐藏；1-隐藏 */
    @Excel(name = "是否隐藏：0-不隐藏；1-隐藏")
    private Long hidden;

    public void setBreedId(Long breedId) 
    {
        this.breedId = breedId;
    }

    public Long getBreedId() 
    {
        return breedId;
    }

    public void setBreedName(String breedName) 
    {
        this.breedName = breedName;
    }

    public String getBreedName() 
    {
        return breedName;
    }

    public void setSpecies(String species) 
    {
        this.species = species;
    }

    public String getSpecies() 
    {
        return species;
    }

    public void setHidden(Long hidden) 
    {
        this.hidden = hidden;
    }

    public Long getHidden() 
    {
        return hidden;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("breedId", getBreedId())
            .append("breedName", getBreedName())
            .append("species", getSpecies())
            .append("hidden", getHidden())
            .append("createTime", getCreateTime())
            .toString();
    }
}
