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
import com.ruoyi.business.domain.TCreditLog;
import com.ruoyi.business.service.ITCreditLogService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 额度变更Controller
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
@RestController
@RequestMapping("/business/creditLog")
public class TCreditLogController extends BaseController
{
    @Autowired
    private ITCreditLogService tCreditLogService;

    /**
     * 查询额度变更列表
     */
    @PreAuthorize("@ss.hasPermi('business:creditLog:list')")
    @GetMapping("/list")
    public TableDataInfo list(TCreditLog tCreditLog)
    {
        startPage();
        List<TCreditLog> list = tCreditLogService.selectTCreditLogList(tCreditLog);
        return getDataTable(list);
    }

    /**
     * 导出额度变更列表
     */
    @PreAuthorize("@ss.hasPermi('business:creditLog:export')")
    @Log(title = "额度变更", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TCreditLog tCreditLog)
    {
        List<TCreditLog> list = tCreditLogService.selectTCreditLogList(tCreditLog);
        ExcelUtil<TCreditLog> util = new ExcelUtil<TCreditLog>(TCreditLog.class);
        util.exportExcel(response, list, "额度变更数据");
    }

    /**
     * 获取额度变更详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:creditLog:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tCreditLogService.selectTCreditLogById(id));
    }

    /**
     * 新增额度变更
     */
    @PreAuthorize("@ss.hasPermi('business:creditLog:add')")
    @Log(title = "额度变更", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TCreditLog tCreditLog)
    {
        return toAjax(tCreditLogService.insertTCreditLog(tCreditLog));
    }

    /**
     * 修改额度变更
     */
    @PreAuthorize("@ss.hasPermi('business:creditLog:edit')")
    @Log(title = "额度变更", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TCreditLog tCreditLog)
    {
        return toAjax(tCreditLogService.updateTCreditLog(tCreditLog));
    }

    /**
     * 删除额度变更
     */
    @PreAuthorize("@ss.hasPermi('business:creditLog:remove')")
    @Log(title = "额度变更", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tCreditLogService.deleteTCreditLogByIds(ids));
    }
}
