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
import com.ruoyi.business.domain.TBankInfo;
import com.ruoyi.business.service.ITBankInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 银行信息Controller
 * 
 * @author ruoyi
 * @date 2026-01-05
 */
@RestController
@RequestMapping("/business/bankInfo")
public class TBankInfoController extends BaseController
{
    @Autowired
    private ITBankInfoService tBankInfoService;

    /**
     * 查询银行信息列表
     */
    @PreAuthorize("@ss.hasPermi('business:bankInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(TBankInfo tBankInfo)
    {
        startPage();
        List<TBankInfo> list = tBankInfoService.selectTBankInfoList(tBankInfo);
        return getDataTable(list);
    }

    /**
     * 导出银行信息列表
     */
    @PreAuthorize("@ss.hasPermi('business:bankInfo:export')")
    @Log(title = "银行信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TBankInfo tBankInfo)
    {
        List<TBankInfo> list = tBankInfoService.selectTBankInfoList(tBankInfo);
        ExcelUtil<TBankInfo> util = new ExcelUtil<TBankInfo>(TBankInfo.class);
        util.exportExcel(response, list, "银行信息数据");
    }

    /**
     * 获取银行信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:bankInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tBankInfoService.selectTBankInfoById(id));
    }

    /**
     * 新增银行信息
     */
    @PreAuthorize("@ss.hasPermi('business:bankInfo:add')")
    @Log(title = "银行信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TBankInfo tBankInfo)
    {
        return toAjax(tBankInfoService.insertTBankInfo(tBankInfo));
    }

    /**
     * 修改银行信息
     */
    @PreAuthorize("@ss.hasPermi('business:bankInfo:edit')")
    @Log(title = "银行信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TBankInfo tBankInfo)
    {
        return toAjax(tBankInfoService.updateTBankInfo(tBankInfo));
    }

    /**
     * 删除银行信息
     */
    @PreAuthorize("@ss.hasPermi('business:bankInfo:remove')")
    @Log(title = "银行信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tBankInfoService.deleteTBankInfoByIds(ids));
    }
}
