package com.ruoyi.hachimiCli.controller;


import com.ruoyi.hachimiCli.domaindto.PetAndImgDto;
import com.ruoyi.hachimiCli.service.PetAndImageService_Cli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetAndImageController_Cli {

    @Autowired
    private PetAndImageService_Cli petService;

    /**
     * 查询宠物列表
     */
    @GetMapping("/list")
    public List<PetAndImgDto> list(PetAndImgDto petAndImgDto) {
        return petService.selectPetList(petAndImgDto);
    }

    /**
     * 根据宠物ID查询详情
     */
    @GetMapping("/{petId}")
    public PetAndImgDto getInfo(@PathVariable("petId") Long petId) {
        return petService.selectPetById(petId);
    }

    /**
     * 新增宠物信息
     */
    @PostMapping
    public int add(@RequestBody PetAndImgDto petAndImgDto) {
        return petService.insertPet(petAndImgDto);
    }

    /**
     * 修改宠物信息
     */
    @PutMapping
    public int edit(@RequestBody PetAndImgDto petAndImgDto) {
        return petService.updatePet(petAndImgDto);
    }

    /**
     * 逻辑删除宠物信息
     */
    @DeleteMapping("/{petId}")
    public int remove(@PathVariable("petId") Long petId) {
        return petService.deletePetById(petId);
    }

    /**
     * 批量逻辑删除宠物信息
     */
    @DeleteMapping("/batch")
    public int removeBatch(@RequestBody Long[] petIds) {
        return petService.deletePetByIds(petIds);
    }

    /**
     * 新增宠物图片
     */
    @PostMapping("/image")
    public int addImage(@RequestBody PetAndImgDto petAndImgDto) {
        return petService.insertPetImage(petAndImgDto);
    }

    /**
     * 逻辑删除宠物的所有图片
     */
    @DeleteMapping("/image/{petId}")
    public int removeImages(@PathVariable("petId") Long petId) {
        return petService.deletePetImagesByPetId(petId);
    }

    /**
     * 根据宠物ID查询所有图片
     */
    @GetMapping("/image/{petId}")
    public List<PetAndImgDto> getImages(@PathVariable("petId") Long petId) {
        return petService.selectPetImagesByPetId(petId);
    }

    /**
     * 新增健康记录
     */
    @PostMapping("/health")
    public int addHealthRecord(@RequestBody PetAndImgDto petAndImgDto) {
        return petService.insertHealthRecord(petAndImgDto);
    }

    /**
     * 修改健康记录
     */
    @PutMapping("/health")
    public int editHealthRecord(@RequestBody PetAndImgDto petAndImgDto) {
        return petService.updateHealthRecord(petAndImgDto);
    }

    /**
     * 逻辑删除健康记录
     */
    @DeleteMapping("/health/{healthId}")
    public int removeHealthRecord(@PathVariable("healthId") Long healthId) {
        return petService.deleteHealthRecordById(healthId);
    }

    /**
     * 根据宠物ID查询健康记录列表
     */
    @GetMapping("/health/{petId}")
    public List<PetAndImgDto> getHealthRecords(@PathVariable("petId") Long petId) {
        return petService.selectHealthRecordsByPetId(petId);
    }

    /**
     * 根据宠物ID查询领养申请ID
     */
    @GetMapping("/apply/{petId}")
    public Long getApplyIdByPetId(@PathVariable("petId") Long petId) {
        return petService.selectApplyIdByPetId(petId);
    }


}
