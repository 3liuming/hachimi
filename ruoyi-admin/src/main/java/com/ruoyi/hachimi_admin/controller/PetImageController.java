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
import com.ruoyi.hachimi_admin.domain.PetImage;
import com.ruoyi.hachimi_admin.service.IPetImageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 宠物图片Controller
 * 
 * @author zhongyu
 * @date 2025-11-03
 */
@RestController
@RequestMapping("/hachimi_admin/pet_image")
public class PetImageController extends BaseController
{
    @Autowired
    private IPetImageService petImageService;

    /**
     * 查询宠物图片列表
     */
    @PreAuthorize("@ss.hasPermi('hachimi_admin:pet_image:list')")
    @GetMapping("/list")
    public TableDataInfo list(PetImage petImage)
    {
        startPage();
        List<PetImage> list = petImageService.selectPetImageList(petImage);
        return getDataTable(list);
    }

    /**
     * 导出宠物图片列表
     */
    @PreAuthorize("@ss.hasPermi('hachimi_admin:pet_image:export')")
    @Log(title = "宠物图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PetImage petImage)
    {
        List<PetImage> list = petImageService.selectPetImageList(petImage);
        ExcelUtil<PetImage> util = new ExcelUtil<PetImage>(PetImage.class);
        util.exportExcel(response, list, "宠物图片数据");
    }

    /**
     * 获取宠物图片详细信息
     */
    @PreAuthorize("@ss.hasPermi('hachimi_admin:pet_image:query')")
    @GetMapping(value = "/{imageId}")
    public AjaxResult getInfo(@PathVariable("imageId") Long imageId)
    {
        return success(petImageService.selectPetImageByImageId(imageId));
    }

    /**
     * 新增宠物图片
     */
    @PreAuthorize("@ss.hasPermi('hachimi_admin:pet_image:add')")
    @Log(title = "宠物图片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PetImage petImage)
    {
        return toAjax(petImageService.insertPetImage(petImage));
    }

    /**
     * 修改宠物图片
     */
    @PreAuthorize("@ss.hasPermi('hachimi_admin:pet_image:edit')")
    @Log(title = "宠物图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PetImage petImage)
    {
        return toAjax(petImageService.updatePetImage(petImage));
    }

    /**
     * 删除宠物图片
     */
    @PreAuthorize("@ss.hasPermi('hachimi_admin:pet_image:remove')")
    @Log(title = "宠物图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{imageIds}")
    public AjaxResult remove(@PathVariable Long[] imageIds)
    {
        return toAjax(petImageService.deletePetImageByImageIds(imageIds));
    }
}
