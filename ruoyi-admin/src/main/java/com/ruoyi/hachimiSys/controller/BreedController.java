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
import com.ruoyi.hachimiSys.domain.Breed;
import com.ruoyi.hachimiSys.service.IBreedService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物品种信息Controller
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@RestController
@RequestMapping("/hachimiSys/breed")
public class BreedController extends BaseController
{
    @Autowired
    private IBreedService breedService;

    /**
     * 查询宠物品种信息列表
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:breed:list')")
    @GetMapping("/list")
    public TableDataInfo list(Breed breed)
    {
        startPage();
        List<Breed> list = breedService.selectBreedList(breed);
        return getDataTable(list);
    }

    /**
     * 导出宠物品种信息列表
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:breed:export')")
    @Log(title = "宠物品种信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Breed breed)
    {
        List<Breed> list = breedService.selectBreedList(breed);
        ExcelUtil<Breed> util = new ExcelUtil<Breed>(Breed.class);
        util.exportExcel(response, list, "宠物品种信息数据");
    }

    /**
     * 获取宠物品种信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:breed:query')")
    @GetMapping(value = "/{breedId}")
    public AjaxResult getInfo(@PathVariable("breedId") Long breedId)
    {
        return success(breedService.selectBreedByBreedId(breedId));
    }

    /**
     * 新增宠物品种信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:breed:add')")
    @Log(title = "宠物品种信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Breed breed)
    {
        return toAjax(breedService.insertBreed(breed));
    }

    /**
     * 修改宠物品种信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:breed:edit')")
    @Log(title = "宠物品种信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Breed breed)
    {
        return toAjax(breedService.updateBreed(breed));
    }

    /**
     * 删除宠物品种信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:breed:remove')")
    @Log(title = "宠物品种信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{breedIds}")
    public AjaxResult remove(@PathVariable Long[] breedIds)
    {
        return toAjax(breedService.deleteBreedByBreedIds(breedIds));
    }
}
