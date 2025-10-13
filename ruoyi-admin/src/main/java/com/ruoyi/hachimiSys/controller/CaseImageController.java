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
import com.ruoyi.hachimiSys.domain.CaseImage;
import com.ruoyi.hachimiSys.service.ICaseImageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 案例图片信息Controller
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@RestController
@RequestMapping("/hachimiSys/caseimage")
public class CaseImageController extends BaseController
{
    @Autowired
    private ICaseImageService caseImageService;

    /**
     * 查询案例图片信息列表
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:caseimage:list')")
    @GetMapping("/list")
    public TableDataInfo list(CaseImage caseImage)
    {
        startPage();
        List<CaseImage> list = caseImageService.selectCaseImageList(caseImage);
        return getDataTable(list);
    }

    /**
     * 导出案例图片信息列表
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:caseimage:export')")
    @Log(title = "案例图片信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CaseImage caseImage)
    {
        List<CaseImage> list = caseImageService.selectCaseImageList(caseImage);
        ExcelUtil<CaseImage> util = new ExcelUtil<CaseImage>(CaseImage.class);
        util.exportExcel(response, list, "案例图片信息数据");
    }

    /**
     * 获取案例图片信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:caseimage:query')")
    @GetMapping(value = "/{caseImageId}")
    public AjaxResult getInfo(@PathVariable("caseImageId") Long caseImageId)
    {
        return success(caseImageService.selectCaseImageByCaseImageId(caseImageId));
    }

    /**
     * 新增案例图片信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:caseimage:add')")
    @Log(title = "案例图片信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CaseImage caseImage)
    {
        return toAjax(caseImageService.insertCaseImage(caseImage));
    }

    /**
     * 修改案例图片信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:caseimage:edit')")
    @Log(title = "案例图片信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CaseImage caseImage)
    {
        return toAjax(caseImageService.updateCaseImage(caseImage));
    }

    /**
     * 删除案例图片信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:caseimage:remove')")
    @Log(title = "案例图片信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{caseImageIds}")
    public AjaxResult remove(@PathVariable Long[] caseImageIds)
    {
        return toAjax(caseImageService.deleteCaseImageByCaseImageIds(caseImageIds));
    }
}
