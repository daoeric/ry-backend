package com.ruoyi.web.controller.business;

import com.ruoyi.business.domain.TPaymentRequest;
import com.ruoyi.business.service.ITPaymentRequestService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.RedisLock;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedisLock redisLock;

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

    /**
     * 手动通过存入订单
     */
    @PreAuthorize("@ss.hasPermi('business:paymentRequest:edit')")
    @Log(title = "手动通过存入订单", businessType = BusinessType.UPDATE)
    @PostMapping("/manualApprove")
    public AjaxResult manualApprove(@RequestBody TPaymentRequest tPaymentRequest)
    {

        BigDecimal realAmount = tPaymentRequest.getRealAmount();
        if (realAmount == null) {
            throw new CustomException("真实金额不能为空");
        }
        String requestId = tPaymentRequest.getRequestId();
        if (StringUtils.isEmpty(requestId)) {
            throw new CustomException("订单号不能为空");
        }
        String key = "doSuccess:"+requestId;
        String remark = tPaymentRequest.getRemark();
        boolean result = false;
        try{
            if (redisLock.tryLock(key, 5, 60, TimeUnit.SECONDS)) {
                result = tPaymentRequestService.doSuccess(requestId,realAmount,remark);
            }
        } catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
        return toAjax(result);
    }
}
