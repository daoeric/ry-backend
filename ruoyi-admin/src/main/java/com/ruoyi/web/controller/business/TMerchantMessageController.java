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
import com.ruoyi.business.domain.TMerchantMessage;
import com.ruoyi.business.service.ITMerchantMessageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商户消息Controller
 * 
 * @author ruoyi
 * @date 2025-12-20
 */
@RestController
@RequestMapping("/business/message")
public class TMerchantMessageController extends BaseController
{
    @Autowired
    private ITMerchantMessageService tMerchantMessageService;

    /**
     * 查询商户消息列表
     */
    @PreAuthorize("@ss.hasPermi('business:message:list')")
    @GetMapping("/list")
    public TableDataInfo list(TMerchantMessage tMerchantMessage)
    {
        startPage();
        List<TMerchantMessage> list = tMerchantMessageService.selectTMerchantMessageList(tMerchantMessage);
        return getDataTable(list);
    }

    /**
     * 导出商户消息列表
     */
    @PreAuthorize("@ss.hasPermi('business:message:export')")
    @Log(title = "商户消息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TMerchantMessage tMerchantMessage)
    {
        List<TMerchantMessage> list = tMerchantMessageService.selectTMerchantMessageList(tMerchantMessage);
        ExcelUtil<TMerchantMessage> util = new ExcelUtil<TMerchantMessage>(TMerchantMessage.class);
        util.exportExcel(response, list, "商户消息数据");
    }

    /**
     * 获取商户消息详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:message:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tMerchantMessageService.selectTMerchantMessageById(id));
    }

    /**
     * 新增商户消息
     */
    @PreAuthorize("@ss.hasPermi('business:message:add')")
    @Log(title = "商户消息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TMerchantMessage tMerchantMessage)
    {
        return toAjax(tMerchantMessageService.insertTMerchantMessage(tMerchantMessage));
    }

    /**
     * 修改商户消息
     */
    @PreAuthorize("@ss.hasPermi('business:message:edit')")
    @Log(title = "商户消息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TMerchantMessage tMerchantMessage)
    {
        return toAjax(tMerchantMessageService.updateTMerchantMessage(tMerchantMessage));
    }

    /**
     * 删除商户消息
     */
    @PreAuthorize("@ss.hasPermi('business:message:remove')")
    @Log(title = "商户消息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tMerchantMessageService.deleteTMerchantMessageByIds(ids));
    }
}
