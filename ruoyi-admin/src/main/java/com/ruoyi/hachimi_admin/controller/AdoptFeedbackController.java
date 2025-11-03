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
import com.ruoyi.hachimi_admin.domain.AdoptFeedback;
import com.ruoyi.hachimi_admin.service.IAdoptFeedbackService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 领养后反馈Controller
 * 
 * @author zhongyu
 * @date 2025-11-03
 */
@RestController
@RequestMapping("/hachimi_admin/feedback")
public class AdoptFeedbackController extends BaseController
{
    @Autowired
    private IAdoptFeedbackService adoptFeedbackService;

    /**
     * 查询领养后反馈列表
     */
    @PreAuthorize("@ss.hasPermi('hachimi_admin:feedback:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdoptFeedback adoptFeedback)
    {
        startPage();
        List<AdoptFeedback> list = adoptFeedbackService.selectAdoptFeedbackList(adoptFeedback);
        return getDataTable(list);
    }

    /**
     * 导出领养后反馈列表
     */
    @PreAuthorize("@ss.hasPermi('hachimi_admin:feedback:export')")
    @Log(title = "领养后反馈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdoptFeedback adoptFeedback)
    {
        List<AdoptFeedback> list = adoptFeedbackService.selectAdoptFeedbackList(adoptFeedback);
        ExcelUtil<AdoptFeedback> util = new ExcelUtil<AdoptFeedback>(AdoptFeedback.class);
        util.exportExcel(response, list, "领养后反馈数据");
    }

    /**
     * 获取领养后反馈详细信息
     */
    @PreAuthorize("@ss.hasPermi('hachimi_admin:feedback:query')")
    @GetMapping(value = "/{feedbackId}")
    public AjaxResult getInfo(@PathVariable("feedbackId") Long feedbackId)
    {
        return success(adoptFeedbackService.selectAdoptFeedbackByFeedbackId(feedbackId));
    }

    /**
     * 新增领养后反馈
     */
    @PreAuthorize("@ss.hasPermi('hachimi_admin:feedback:add')")
    @Log(title = "领养后反馈", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdoptFeedback adoptFeedback)
    {
        return toAjax(adoptFeedbackService.insertAdoptFeedback(adoptFeedback));
    }

    /**
     * 修改领养后反馈
     */
    @PreAuthorize("@ss.hasPermi('hachimi_admin:feedback:edit')")
    @Log(title = "领养后反馈", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdoptFeedback adoptFeedback)
    {
        return toAjax(adoptFeedbackService.updateAdoptFeedback(adoptFeedback));
    }

    /**
     * 删除领养后反馈
     */
    @PreAuthorize("@ss.hasPermi('hachimi_admin:feedback:remove')")
    @Log(title = "领养后反馈", businessType = BusinessType.DELETE)
	@DeleteMapping("/{feedbackIds}")
    public AjaxResult remove(@PathVariable Long[] feedbackIds)
    {
        return toAjax(adoptFeedbackService.deleteAdoptFeedbackByFeedbackIds(feedbackIds));
    }
}
