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
import com.ruoyi.hachimiSys.domain.AdoptFeedback;
import com.ruoyi.hachimiSys.service.IAdoptFeedbackService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 领养后反馈信息Controller
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@RestController
@RequestMapping("/hachimiSys/feedback")
public class AdoptFeedbackController extends BaseController
{
    @Autowired
    private IAdoptFeedbackService adoptFeedbackService;

    /**
     * 查询领养后反馈信息列表
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:feedback:list')")
    @GetMapping("/list")
    public TableDataInfo list(AdoptFeedback adoptFeedback)
    {
        startPage();
        List<AdoptFeedback> list = adoptFeedbackService.selectAdoptFeedbackList(adoptFeedback);
        return getDataTable(list);
    }

    /**
     * 导出领养后反馈信息列表
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:feedback:export')")
    @Log(title = "领养后反馈信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AdoptFeedback adoptFeedback)
    {
        List<AdoptFeedback> list = adoptFeedbackService.selectAdoptFeedbackList(adoptFeedback);
        ExcelUtil<AdoptFeedback> util = new ExcelUtil<AdoptFeedback>(AdoptFeedback.class);
        util.exportExcel(response, list, "领养后反馈信息数据");
    }

    /**
     * 获取领养后反馈信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:feedback:query')")
    @GetMapping(value = "/{feedbackId}")
    public AjaxResult getInfo(@PathVariable("feedbackId") Long feedbackId)
    {
        return success(adoptFeedbackService.selectAdoptFeedbackByFeedbackId(feedbackId));
    }

    /**
     * 新增领养后反馈信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:feedback:add')")
    @Log(title = "领养后反馈信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AdoptFeedback adoptFeedback)
    {
        return toAjax(adoptFeedbackService.insertAdoptFeedback(adoptFeedback));
    }

    /**
     * 修改领养后反馈信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:feedback:edit')")
    @Log(title = "领养后反馈信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AdoptFeedback adoptFeedback)
    {
        return toAjax(adoptFeedbackService.updateAdoptFeedback(adoptFeedback));
    }

    /**
     * 删除领养后反馈信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:feedback:remove')")
    @Log(title = "领养后反馈信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{feedbackIds}")
    public AjaxResult remove(@PathVariable Long[] feedbackIds)
    {
        return toAjax(adoptFeedbackService.deleteAdoptFeedbackByFeedbackIds(feedbackIds));
    }
}
