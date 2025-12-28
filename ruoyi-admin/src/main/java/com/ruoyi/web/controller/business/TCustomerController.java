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
import com.ruoyi.business.domain.TCustomer;
import com.ruoyi.business.service.ITCustomerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户管理Controller
 * 
 * @author ruoyi
 * @date 2025-12-08
 */
@RestController
@RequestMapping("/business/customer")
public class TCustomerController extends BaseController
{
    @Autowired
    private ITCustomerService tCustomerService;

    /**
     * 查询用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('business:customer:list')")
    @GetMapping("/list")
    public TableDataInfo list(TCustomer tCustomer)
    {
        startPage();
        List<TCustomer> list = tCustomerService.selectTCustomerList(tCustomer);
        return getDataTable(list);
    }

    /**
     * 导出用户管理列表
     */
    @PreAuthorize("@ss.hasPermi('business:customer:export')")
    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TCustomer tCustomer)
    {
        List<TCustomer> list = tCustomerService.selectTCustomerList(tCustomer);
        ExcelUtil<TCustomer> util = new ExcelUtil<TCustomer>(TCustomer.class);
        util.exportExcel(response, list, "用户管理数据");
    }

    /**
     * 获取用户管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:customer:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tCustomerService.selectTCustomerById(id));
    }

    /**
     * 新增用户管理
     */
    @PreAuthorize("@ss.hasPermi('business:customer:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TCustomer tCustomer)
    {
        return toAjax(tCustomerService.insertTCustomer(tCustomer));
    }

    /**
     * 修改用户管理
     */
    @PreAuthorize("@ss.hasPermi('business:customer:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TCustomer tCustomer)
    {
        return toAjax(tCustomerService.updateTCustomer(tCustomer));
    }

    /**
     * 修改用户管理
     */
    @PreAuthorize("@ss.hasPermi('business:customer:edit')")
    @Log(title = "续签会员", businessType = BusinessType.UPDATE)
    @PutMapping("/renew")
    public AjaxResult renewVip(@RequestBody TCustomer tCustomer)
    {
        return toAjax(tCustomerService.renew(tCustomer.getId(),tCustomer.getExpireTime()));
    }

    /**
     * 删除用户管理
     */
    @PreAuthorize("@ss.hasPermi('business:customer:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tCustomerService.deleteTCustomerByIds(ids));
    }
}
