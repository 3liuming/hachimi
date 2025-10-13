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
import com.ruoyi.hachimiSys.domain.Pet;
import com.ruoyi.hachimiSys.service.IPetService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物信息Controller
 * 
 * @author ruoyi
 * @date 2025-10-10
 */
@RestController
@RequestMapping("/hachimiSys/pet")
public class PetController extends BaseController
{
    @Autowired
    private IPetService petService;

    /**
     * 查询宠物信息列表
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:pet:list')")
    @GetMapping("/list")
    public TableDataInfo list(Pet pet)
    {
        startPage();
        List<Pet> list = petService.selectPetList(pet);
        return getDataTable(list);
    }

    /**
     * 导出宠物信息列表
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:pet:export')")
    @Log(title = "宠物信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Pet pet)
    {
        List<Pet> list = petService.selectPetList(pet);
        ExcelUtil<Pet> util = new ExcelUtil<Pet>(Pet.class);
        util.exportExcel(response, list, "宠物信息数据");
    }

    /**
     * 获取宠物信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:pet:query')")
    @GetMapping(value = "/{petId}")
    public AjaxResult getInfo(@PathVariable("petId") Long petId)
    {
        return success(petService.selectPetByPetId(petId));
    }

    /**
     * 新增宠物信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:pet:add')")
    @Log(title = "宠物信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Pet pet)
    {
        return toAjax(petService.insertPet(pet));
    }

    /**
     * 修改宠物信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:pet:edit')")
    @Log(title = "宠物信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Pet pet)
    {
        return toAjax(petService.updatePet(pet));
    }

    /**
     * 删除宠物信息
     */
    @PreAuthorize("@ss.hasPermi('hachimiSys:pet:remove')")
    @Log(title = "宠物信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{petIds}")
    public AjaxResult remove(@PathVariable Long[] petIds)
    {
        return toAjax(petService.deletePetByPetIds(petIds));
    }
}
