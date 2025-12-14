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
import com.ruoyi.business.domain.TPaymentRequest;
import com.ruoyi.business.service.ITPaymentRequestService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 存入订单Controller
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
@RestController
@RequestMapping("/business/paymentRequest")
public class TPaymentRequestController extends BaseController
{
    @Autowired
    private ITPaymentRequestService tPaymentRequestService;

    /**
     * 查询存入订单列表
     */
    @PreAuthorize("@ss.hasPermi('business:paymentRequest:list')")
    @GetMapping("/list")
    public TableDataInfo list(TPaymentRequest tPaymentRequest)
    {
        startPage();
        List<TPaymentRequest> list = tPaymentRequestService.selectTPaymentRequestList(tPaymentRequest);
        return getDataTable(list);
    }

    /**
     * 导出存入订单列表
     */
    @PreAuthorize("@ss.hasPermi('business:paymentRequest:export')")
    @Log(title = "存入订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TPaymentRequest tPaymentRequest)
    {
        List<TPaymentRequest> list = tPaymentRequestService.selectTPaymentRequestList(tPaymentRequest);
        ExcelUtil<TPaymentRequest> util = new ExcelUtil<TPaymentRequest>(TPaymentRequest.class);
        util.exportExcel(response, list, "存入订单数据");
    }

    /**
     * 获取存入订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:paymentRequest:query')")
    @GetMapping(value = "/{requestId}")
    public AjaxResult getInfo(@PathVariable("requestId") String requestId)
    {
        return success(tPaymentRequestService.selectTPaymentRequestByRequestId(requestId));
    }

    /**
     * 新增存入订单
     */
    @PreAuthorize("@ss.hasPermi('business:paymentRequest:add')")
    @Log(title = "存入订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TPaymentRequest tPaymentRequest)
    {
        return toAjax(tPaymentRequestService.insertTPaymentRequest(tPaymentRequest));
    }

    /**
     * 修改存入订单
     */
    @PreAuthorize("@ss.hasPermi('business:paymentRequest:edit')")
    @Log(title = "存入订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TPaymentRequest tPaymentRequest)
    {
        return toAjax(tPaymentRequestService.updateTPaymentRequest(tPaymentRequest));
    }

    /**
     * 删除存入订单
     */
    @PreAuthorize("@ss.hasPermi('business:paymentRequest:remove')")
    @Log(title = "存入订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{requestIds}")
    public AjaxResult remove(@PathVariable String[] requestIds)
    {
        return toAjax(tPaymentRequestService.deleteTPaymentRequestByRequestIds(requestIds));
    }
}
