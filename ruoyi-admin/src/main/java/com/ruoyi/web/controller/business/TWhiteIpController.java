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
import com.ruoyi.business.domain.TWhiteIp;
import com.ruoyi.business.service.ITWhiteIpService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 上游信息Controller
 * 
 * @author ruoyi
 * @date 2026-01-04
 */
@RestController
@RequestMapping("/business/ip")
public class TWhiteIpController extends BaseController
{
    @Autowired
    private ITWhiteIpService tWhiteIpService;

    /**
     * 查询上游信息列表
     */
    @PreAuthorize("@ss.hasPermi('business:ip:list')")
    @GetMapping("/list")
    public TableDataInfo list(TWhiteIp tWhiteIp)
    {
        startPage();
        List<TWhiteIp> list = tWhiteIpService.selectTWhiteIpList(tWhiteIp);
        return getDataTable(list);
    }

    /**
     * 导出上游信息列表
     */
    @PreAuthorize("@ss.hasPermi('business:ip:export')")
    @Log(title = "上游信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TWhiteIp tWhiteIp)
    {
        List<TWhiteIp> list = tWhiteIpService.selectTWhiteIpList(tWhiteIp);
        ExcelUtil<TWhiteIp> util = new ExcelUtil<TWhiteIp>(TWhiteIp.class);
        util.exportExcel(response, list, "上游信息数据");
    }

    /**
     * 获取上游信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:ip:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(tWhiteIpService.selectTWhiteIpById(id));
    }

    /**
     * 新增上游信息
     */
    @PreAuthorize("@ss.hasPermi('business:ip:add')")
    @Log(title = "上游信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TWhiteIp tWhiteIp)
    {
        return toAjax(tWhiteIpService.insertTWhiteIp(tWhiteIp));
    }

    /**
     * 修改上游信息
     */
    @PreAuthorize("@ss.hasPermi('business:ip:edit')")
    @Log(title = "上游信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TWhiteIp tWhiteIp)
    {
        return toAjax(tWhiteIpService.updateTWhiteIp(tWhiteIp));
    }

    /**
     * 删除上游信息
     */
    @PreAuthorize("@ss.hasPermi('business:ip:remove')")
    @Log(title = "上游信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(tWhiteIpService.deleteTWhiteIpByIds(ids));
    }
}
