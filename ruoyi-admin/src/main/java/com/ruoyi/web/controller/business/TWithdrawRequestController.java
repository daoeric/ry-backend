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
import com.ruoyi.business.domain.TWithdrawRequest;
import com.ruoyi.business.service.ITWithdrawRequestService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 提现订单Controller
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
@RestController
@RequestMapping("/business/withdrawRequest")
public class TWithdrawRequestController extends BaseController
{
    @Autowired
    private ITWithdrawRequestService tWithdrawRequestService;

    /**
     * 查询提现订单列表
     */
    @PreAuthorize("@ss.hasPermi('business:withdrawRequest:list')")
    @GetMapping("/list")
    public TableDataInfo list(TWithdrawRequest tWithdrawRequest)
    {
        startPage();
        List<TWithdrawRequest> list = tWithdrawRequestService.selectTWithdrawRequestList(tWithdrawRequest);
        return getDataTable(list);
    }

    /**
     * 导出提现订单列表
     */
    @PreAuthorize("@ss.hasPermi('business:withdrawRequest:export')")
    @Log(title = "提现订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TWithdrawRequest tWithdrawRequest)
    {
        List<TWithdrawRequest> list = tWithdrawRequestService.selectTWithdrawRequestList(tWithdrawRequest);
        ExcelUtil<TWithdrawRequest> util = new ExcelUtil<TWithdrawRequest>(TWithdrawRequest.class);
        util.exportExcel(response, list, "提现订单数据");
    }

    /**
     * 获取提现订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:withdrawRequest:query')")
    @GetMapping(value = "/{withdrawId}")
    public AjaxResult getInfo(@PathVariable("withdrawId") String withdrawId)
    {
        return success(tWithdrawRequestService.selectTWithdrawRequestByWithdrawId(withdrawId));
    }

    /**
     * 新增提现订单
     */
    @PreAuthorize("@ss.hasPermi('business:withdrawRequest:add')")
    @Log(title = "提现订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TWithdrawRequest tWithdrawRequest)
    {
        return toAjax(tWithdrawRequestService.insertTWithdrawRequest(tWithdrawRequest));
    }

    /**
     * 修改提现订单
     */
    @PreAuthorize("@ss.hasPermi('business:withdrawRequest:edit')")
    @Log(title = "提现订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TWithdrawRequest tWithdrawRequest)
    {
        return toAjax(tWithdrawRequestService.updateTWithdrawRequest(tWithdrawRequest));
    }

    /**
     * 删除提现订单
     */
    @PreAuthorize("@ss.hasPermi('business:withdrawRequest:remove')")
    @Log(title = "提现订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{withdrawIds}")
    public AjaxResult remove(@PathVariable String[] withdrawIds)
    {
        return toAjax(tWithdrawRequestService.deleteTWithdrawRequestByWithdrawIds(withdrawIds));
    }
}
