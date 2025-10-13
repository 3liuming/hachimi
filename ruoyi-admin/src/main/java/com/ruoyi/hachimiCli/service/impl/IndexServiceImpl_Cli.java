package com.ruoyi.hachimiCli.service.impl;

import com.ruoyi.hachimiCli.domaindto.IndexImgDto;
import com.ruoyi.hachimiCli.mapper.IndexMapper_Cli;
import com.ruoyi.hachimiCli.service.IndexService_Cli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexServiceImpl_Cli implements IndexService_Cli {

    @Autowired
    private IndexMapper_Cli indexMapper;

    @Override
    public List<IndexImgDto> getIndexImgList() {
        // 调用mapper查询符合条件的数据
        return indexMapper.selectIndexImgs();
    }
}
