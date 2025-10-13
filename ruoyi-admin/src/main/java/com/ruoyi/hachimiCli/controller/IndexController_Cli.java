package com.ruoyi.hachimiCli.controller;

import com.ruoyi.hachimiCli.domaindto.IndexImgDto;
import com.ruoyi.hachimiCli.service.IndexService_Cli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/index")
public class IndexController_Cli {

    @Autowired
    private IndexService_Cli indexImgService;

    @GetMapping("/images")
    public List<IndexImgDto> getIndexImages() {
        return indexImgService.getIndexImgList();
    }
}
