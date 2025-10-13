package com.ruoyi.hachimiSys.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.hachimiSys.domain.RescueCase;
import com.ruoyi.hachimiSys.service.IRescueCaseService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 救助案例信息Controller
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@RestController
@RequestMapping("/hachimiSys/case")
public class RescueCaseController extends BaseController
{
    @Autowired
    private IRescueCaseService rescueCaseService;

    /**
     * 查询救助案例信息列表
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:case:list')")
    @GetMapping("/list")
    public TableDataInfo list(RescueCase rescueCase)
    {
        startPage();
        List<RescueCase> list = rescueCaseService.selectRescueCaseList(rescueCase);
        return getDataTable(list);
    }

    /**
     * 导出救助案例信息列表
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:case:export')")
    @Log(title = "救助案例信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RescueCase rescueCase)
    {
        List<RescueCase> list = rescueCaseService.selectRescueCaseList(rescueCase);
        ExcelUtil<RescueCase> util = new ExcelUtil<RescueCase>(RescueCase.class);
        util.exportExcel(response, list, "救助案例信息数据");
    }

    /**
     * 获取救助案例信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:case:query')")
    @GetMapping(value = "/{caseId}")
    public AjaxResult getInfo(@PathVariable("caseId") Long caseId)
    {
        return success(rescueCaseService.selectRescueCaseByCaseId(caseId));
    }

    /**
     * 新增救助案例信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:case:add')")
    @Log(title = "救助案例信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RescueCase rescueCase)
    {
        return toAjax(rescueCaseService.insertRescueCase(rescueCase));
    }

    /**
     * 修改救助案例信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:case:edit')")
    @Log(title = "救助案例信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RescueCase rescueCase)
    {
        return toAjax(rescueCaseService.updateRescueCase(rescueCase));
    }

    /**
     * 删除救助案例信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:case:remove')")
    @Log(title = "救助案例信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{caseIds}")
    public AjaxResult remove(@PathVariable Long[] caseIds)
    {
        return toAjax(rescueCaseService.deleteRescueCaseByCaseIds(caseIds));
    }
}
