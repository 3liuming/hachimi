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
import com.ruoyi.hachimiSys.domain.PetHealthRecord;
import com.ruoyi.hachimiSys.service.IPetHealthRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物健康档案信息Controller
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@RestController
@RequestMapping("/hachimiSys/record")
public class PetHealthRecordController extends BaseController
{
    @Autowired
    private IPetHealthRecordService petHealthRecordService;

    /**
     * 查询宠物健康档案信息列表
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(PetHealthRecord petHealthRecord)
    {
        startPage();
        List<PetHealthRecord> list = petHealthRecordService.selectPetHealthRecordList(petHealthRecord);
        return getDataTable(list);
    }

    /**
     * 导出宠物健康档案信息列表
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:record:export')")
    @Log(title = "宠物健康档案信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PetHealthRecord petHealthRecord)
    {
        List<PetHealthRecord> list = petHealthRecordService.selectPetHealthRecordList(petHealthRecord);
        ExcelUtil<PetHealthRecord> util = new ExcelUtil<PetHealthRecord>(PetHealthRecord.class);
        util.exportExcel(response, list, "宠物健康档案信息数据");
    }

    /**
     * 获取宠物健康档案信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:record:query')")
    @GetMapping(value = "/{healthId}")
    public AjaxResult getInfo(@PathVariable("healthId") Long healthId)
    {
        return success(petHealthRecordService.selectPetHealthRecordByHealthId(healthId));
    }

    /**
     * 新增宠物健康档案信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:record:add')")
    @Log(title = "宠物健康档案信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PetHealthRecord petHealthRecord)
    {
        return toAjax(petHealthRecordService.insertPetHealthRecord(petHealthRecord));
    }

    /**
     * 修改宠物健康档案信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:record:edit')")
    @Log(title = "宠物健康档案信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PetHealthRecord petHealthRecord)
    {
        return toAjax(petHealthRecordService.updatePetHealthRecord(petHealthRecord));
    }

    /**
     * 删除宠物健康档案信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:record:remove')")
    @Log(title = "宠物健康档案信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{healthIds}")
    public AjaxResult remove(@PathVariable Long[] healthIds)
    {
        return toAjax(petHealthRecordService.deletePetHealthRecordByHealthIds(healthIds));
    }
}
