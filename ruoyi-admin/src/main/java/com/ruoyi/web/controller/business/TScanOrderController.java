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
import com.ruoyi.business.domain.TScanOrder;
import com.ruoyi.business.service.ITScanOrderService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 扫描订单Controller
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
@RestController
@RequestMapping("/business/scanOrder")
public class TScanOrderController extends BaseController
{
    @Autowired
    private ITScanOrderService tScanOrderService;

    /**
     * 查询扫描订单列表
     */
    @PreAuthorize("@ss.hasPermi('business:scanOrder:list')")
    @GetMapping("/list")
    public TableDataInfo list(TScanOrder tScanOrder)
    {
        startPage();
        List<TScanOrder> list = tScanOrderService.selectTScanOrderList(tScanOrder);
        return getDataTable(list);
    }

    /**
     * 导出扫描订单列表
     */
    @PreAuthorize("@ss.hasPermi('business:scanOrder:export')")
    @Log(title = "扫描订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TScanOrder tScanOrder)
    {
        List<TScanOrder> list = tScanOrderService.selectTScanOrderList(tScanOrder);
        ExcelUtil<TScanOrder> util = new ExcelUtil<TScanOrder>(TScanOrder.class);
        util.exportExcel(response, list, "扫描订单数据");
    }

    /**
     * 获取扫描订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:scanOrder:query')")
    @GetMapping(value = "/{orderNo}")
    public AjaxResult getInfo(@PathVariable("orderNo") String orderNo)
    {
        return success(tScanOrderService.selectTScanOrderByOrderNo(orderNo));
    }

    /**
     * 新增扫描订单
     */
    @PreAuthorize("@ss.hasPermi('business:scanOrder:add')")
    @Log(title = "扫描订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TScanOrder tScanOrder)
    {
        return toAjax(tScanOrderService.insertTScanOrder(tScanOrder));
    }

    /**
     * 修改扫描订单
     */
    @PreAuthorize("@ss.hasPermi('business:scanOrder:edit')")
    @Log(title = "扫描订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TScanOrder tScanOrder)
    {
        return toAjax(tScanOrderService.updateTScanOrder(tScanOrder));
    }

    /**
     * 删除扫描订单
     */
    @PreAuthorize("@ss.hasPermi('business:scanOrder:remove')")
    @Log(title = "扫描订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderNos}")
    public AjaxResult remove(@PathVariable String[] orderNos)
    {
        return toAjax(tScanOrderService.deleteTScanOrderByOrderNos(orderNos));
    }
}
