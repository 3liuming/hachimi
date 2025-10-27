package com.ruoyi.hachimiCli.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.hachimiCli.domaindto.PetAndImgDto;
import com.ruoyi.hachimiCli.service.PetAndImageService_Cli;
import com.ruoyi.hachimiSys.domain.AdoptionApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pet")
public class PetAndImageController_Cli {

    @Autowired
    private PetAndImageService_Cli petService;

    /**
     * 查询宠物列表
     */
    /**
     * 查询宠物列表
     */
    @GetMapping("/list")
    public AjaxResult list(PetAndImgDto petAndImgDto) {
        Integer pageNum = petAndImgDto.getPageNum() != null ? petAndImgDto.getPageNum() : 1;
        Integer pageSize = petAndImgDto.getPageSize() != null ? petAndImgDto.getPageSize() : 10;

        PageHelper.startPage(pageNum, pageSize);
        List<PetAndImgDto> list = petService.selectPetList(petAndImgDto);

        // 手动构造分页结果
        PageInfo<PetAndImgDto> pageInfo = new PageInfo<>(list);

        Map<String, Object> result = new HashMap<>();
        result.put("total", pageInfo.getTotal());  // 总记录数
        result.put("rows", pageInfo.getList());     // 数据列表

        return AjaxResult.success(result);
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
    public Long getApplyIdByPetId(@PathVariable("petId") Long petId,
                                  HttpServletRequest request) {
        // 从请求中获取userId（根据您的实际情况）
        Long userId =(Long) request.getAttribute("userId");
        return petService.selectApplyIdByPetId(petId,userId);
    }

    @PutMapping("/adoptionApply")
    public AjaxResult inputApplyByPetIdAndUserId(@RequestBody AdoptionApply adoptionApply,
                                                 HttpServletRequest request) {
        try {
            // 从请求中获取userId（根据您的实际情况）
            Long userId =(Long) request.getAttribute("userId");

            // 调用service
            Long applyId = petService.inputApplyByPetIdAndUserId(adoptionApply, userId);

            // 返回成功结果
            return AjaxResult.success("申请提交成功", applyId);

        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
    }

}
