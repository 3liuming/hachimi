package com.ruoyi.hachimiCli.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.hachimiCli.domaindto.CaseAndImgDto;
import com.ruoyi.hachimiCli.service.RescueCaseService_Cli;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 救助案例Controller
 */
@RestController
@RequestMapping("/hachimi/case")
public class RescueCaseControllerCli {

    @Autowired
    private RescueCaseService_Cli rescueCaseService;

    /**
     * 查询救助案例列表（支持筛选）
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody CaseAndImgDto dto) {
        List<CaseAndImgDto> list = rescueCaseService.selectRescueCaseList(dto);
        return AjaxResult.success(list);
    }

    /**
     * 根据案例ID查询救助案例详情
     */
    @GetMapping("/{caseId}")
    public AjaxResult getInfo(@PathVariable("caseId") Long caseId) {
        CaseAndImgDto dto = rescueCaseService.selectRescueCaseById(caseId);
        return AjaxResult.success(dto);
    }

    /**
     * 新增救助案例
     */
    @PostMapping("/createcase")
    public AjaxResult add(@RequestBody CaseAndImgDto dto, HttpServletRequest request) {
        return AjaxResult.success(rescueCaseService.insertRescueCase(dto,(Long)request.getAttribute("userId")));
    }

    /**
     * 修改救助案例
     */
    @PutMapping
    public AjaxResult edit(@RequestBody CaseAndImgDto dto) {
        return AjaxResult.success(rescueCaseService.updateRescueCase(dto));
    }

    /**
     * 删除救助案例（逻辑删除）
     */
    @DeleteMapping("/{caseId}")
    public AjaxResult remove(@PathVariable("caseId") Long caseId) {
        return AjaxResult.success(rescueCaseService.deleteRescueCaseById(caseId));
    }

    /**
     * 批量删除救助案例（逻辑删除）
     */
    @DeleteMapping("/batch/{caseIds}")
    public AjaxResult removeBatch(@PathVariable Long[] caseIds) {
        return AjaxResult.success(rescueCaseService.deleteRescueCaseByIds(caseIds));
    }

    /**
     * 增加浏览量
     */
    @PutMapping("/view/{caseId}")
    public AjaxResult incrementView(@PathVariable("caseId") Long caseId) {
        return AjaxResult.success(rescueCaseService.incrementViewCount(caseId));
    }
}