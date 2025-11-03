package com.ruoyi.hachimi_admin.controller;

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
import com.ruoyi.hachimi_admin.domain.AdoptionApply;
import com.ruoyi.hachimi_admin.service.IAdoptionApplyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 领养申请Controller
 * 
 * @author zhongyu
 * @date 2025-11-03
 */
@RestController
@RequestMapping("/hachimi_admin/apply")
public class AdoptionApplyController extends BaseController
{
    @Autowired
    private IAdoptionApplyService adoptionApplyService;

    /**
     * 查询领养申请列表
     */
    @PreAuthorize("@ss.hasPermi('hachimi_admin:apply:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdoptionApply adoptionApply)
    {
        startPage();
        List<AdoptionApply> list = adoptionApplyService.selectAdoptionApplyList(adoptionApply);
        return getDataTable(list);
    }

    /**
     * 导出领养申请列表
     */
    @PreAuthorize("@ss.hasPermi('hachimi_admin:apply:export')")
    @Log(title = "领养申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdoptionApply adoptionApply)
    {
        List<AdoptionApply> list = adoptionApplyService.selectAdoptionApplyList(adoptionApply);
        ExcelUtil<AdoptionApply> util = new ExcelUtil<AdoptionApply>(AdoptionApply.class);
        util.exportExcel(response, list, "领养申请数据");
    }

    /**
     * 获取领养申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('hachimi_admin:apply:query')")
    @GetMapping(value = "/{applyId}")
    public AjaxResult getInfo(@PathVariable("applyId") Long applyId)
    {
        return success(adoptionApplyService.selectAdoptionApplyByApplyId(applyId));
    }

    /**
     * 新增领养申请
     */
    @PreAuthorize("@ss.hasPermi('hachimi_admin:apply:add')")
    @Log(title = "领养申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdoptionApply adoptionApply)
    {
        return toAjax(adoptionApplyService.insertAdoptionApply(adoptionApply));
    }

    /**
     * 修改领养申请
     */
    @PreAuthorize("@ss.hasPermi('hachimi_admin:apply:edit')")
    @Log(title = "领养申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdoptionApply adoptionApply)
    {
        return toAjax(adoptionApplyService.updateAdoptionApply(adoptionApply));
    }

    /**
     * 删除领养申请
     */
    @PreAuthorize("@ss.hasPermi('hachimi_admin:apply:remove')")
    @Log(title = "领养申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{applyIds}")
    public AjaxResult remove(@PathVariable Long[] applyIds)
    {
        return toAjax(adoptionApplyService.deleteAdoptionApplyByApplyIds(applyIds));
    }
}
