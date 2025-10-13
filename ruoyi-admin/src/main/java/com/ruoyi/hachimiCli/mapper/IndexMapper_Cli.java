package com.ruoyi.hachimiCli.mapper;

import com.ruoyi.hachimiCli.domaindto.IndexImgDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IndexMapper_Cli {

    List<IndexImgDto> selectIndexImgs();
}
