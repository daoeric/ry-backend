package com.ruoyi.web.controller.business;

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
import com.ruoyi.business.domain.TVip;
import com.ruoyi.business.service.ITVipService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * VIP管理Controller
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
@RestController
@RequestMapping("/business/vip")
public class TVipController extends BaseController
{
    @Autowired
    private ITVipService tVipService;

    /**
     * 查询VIP管理列表
     */
    @PreAuthorize("@ss.hasPermi('business:vip:list')")
    @GetMapping("/list")
    public TableDataInfo list(TVip tVip)
    {
        startPage();
        List<TVip> list = tVipService.selectTVipList(tVip);
        return getDataTable(list);
    }

    /**
     * 导出VIP管理列表
     */
    @PreAuthorize("@ss.hasPermi('business:vip:export')")
    @Log(title = "VIP管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TVip tVip)
    {
        List<TVip> list = tVipService.selectTVipList(tVip);
        ExcelUtil<TVip> util = new ExcelUtil<TVip>(TVip.class);
        util.exportExcel(response, list, "VIP管理数据");
    }

    /**
     * 获取VIP管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:vip:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(tVipService.selectTVipById(id));
    }

    /**
     * 新增VIP管理
     */
    @PreAuthorize("@ss.hasPermi('business:vip:add')")
    @Log(title = "VIP管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TVip tVip)
    {
        return toAjax(tVipService.insertTVip(tVip));
    }

    /**
     * 修改VIP管理
     */
    @PreAuthorize("@ss.hasPermi('business:vip:edit')")
    @Log(title = "VIP管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TVip tVip)
    {
        return toAjax(tVipService.updateTVip(tVip));
    }

    /**
     * 删除VIP管理
     */
    @PreAuthorize("@ss.hasPermi('business:vip:remove')")
    @Log(title = "VIP管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(tVipService.deleteTVipByIds(ids));
    }
}
