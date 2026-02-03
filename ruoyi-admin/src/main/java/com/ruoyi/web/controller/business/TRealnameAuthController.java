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
import com.ruoyi.business.domain.TRealnameAuth;
import com.ruoyi.business.service.ITRealnameAuthService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 实名认证Controller
 * 
 * @author ruoyi
 * @date 2025-12-19
 */
@RestController
@RequestMapping("/business/auth")
public class TRealnameAuthController extends BaseController
{
    @Autowired
    private ITRealnameAuthService tRealnameAuthService;

    /**
     * 查询实名认证列表
     */
    @PreAuthorize("@ss.hasPermi('business:auth:list')")
    @GetMapping("/list")
    public TableDataInfo list(TRealnameAuth tRealnameAuth)
    {
        startPage();
        List<TRealnameAuth> list = tRealnameAuthService.selectTRealnameAuthList(tRealnameAuth);
        return getDataTable(list);
    }

    /**
     * 导出实名认证列表
     */
    @PreAuthorize("@ss.hasPermi('business:auth:export')")
    @Log(title = "实名认证", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TRealnameAuth tRealnameAuth)
    {
        List<TRealnameAuth> list = tRealnameAuthService.selectTRealnameAuthList(tRealnameAuth);
        ExcelUtil<TRealnameAuth> util = new ExcelUtil<TRealnameAuth>(TRealnameAuth.class);
        util.exportExcel(response, list, "实名认证数据");
    }

    /**
     * 获取实名认证详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:auth:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tRealnameAuthService.selectTRealnameAuthById(id));
    }

    /**
     * 新增实名认证
     */
    @PreAuthorize("@ss.hasPermi('business:auth:add')")
    @Log(title = "实名认证", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TRealnameAuth tRealnameAuth)
    {
        return toAjax(tRealnameAuthService.insertTRealnameAuth(tRealnameAuth));
    }

    /**
     * 修改实名认证
     */
    @PreAuthorize("@ss.hasPermi('business:auth:edit')")
    @Log(title = "实名认证", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TRealnameAuth tRealnameAuth)
    {
        return toAjax(tRealnameAuthService.updateTRealnameAuth(tRealnameAuth));
    }

    /**
     * 删除实名认证
     */
    @PreAuthorize("@ss.hasPermi('business:auth:remove')")
    @Log(title = "实名认证", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tRealnameAuthService.deleteTRealnameAuthByIds(ids));
    }

    @PreAuthorize("@ss.hasPermi('business:auth:edit')")
    @Log(title = "实名认证审核", businessType = BusinessType.UPDATE)
    @PutMapping("/approve")
    public AjaxResult approve(@RequestBody TRealnameAuth tRealnameAuth)
    {
        boolean result = tRealnameAuthService.approve(tRealnameAuth.getId(), 1, tRealnameAuth.getAuditReason(),getUsername());
        return AjaxResult.success(result);
    }

    @PreAuthorize("@ss.hasPermi('business:auth:edit')")
    @Log(title = "实名认证审核", businessType = BusinessType.UPDATE)
    @PutMapping("/reject")
    public AjaxResult reject(@RequestBody TRealnameAuth tRealnameAuth)
    {
        boolean result = tRealnameAuthService.approve(tRealnameAuth.getId(), 2 ,tRealnameAuth.getAuditReason(),getUsername());
        return AjaxResult.success(result);
    }


}
