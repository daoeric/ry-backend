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
import com.ruoyi.business.domain.TChannelRujin;
import com.ruoyi.business.service.ITChannelRujinService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 入金渠道Controller
 * 
 * @author ruoyi
 * @date 2026-01-04
 */
@RestController
@RequestMapping("/business/rujin")
public class TChannelRujinController extends BaseController
{
    @Autowired
    private ITChannelRujinService tChannelRujinService;

    /**
     * 查询入金渠道列表
     */
    @PreAuthorize("@ss.hasPermi('business:rujin:list')")
    @GetMapping("/list")
    public TableDataInfo list(TChannelRujin tChannelRujin)
    {
        startPage();
        List<TChannelRujin> list = tChannelRujinService.selectTChannelRujinList(tChannelRujin);
        return getDataTable(list);
    }

    /**
     * 导出入金渠道列表
     */
    @PreAuthorize("@ss.hasPermi('business:rujin:export')")
    @Log(title = "入金渠道", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TChannelRujin tChannelRujin)
    {
        List<TChannelRujin> list = tChannelRujinService.selectTChannelRujinList(tChannelRujin);
        ExcelUtil<TChannelRujin> util = new ExcelUtil<TChannelRujin>(TChannelRujin.class);
        util.exportExcel(response, list, "入金渠道数据");
    }

    /**
     * 获取入金渠道详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:rujin:query')")
    @GetMapping(value = "/{channelId}")
    public AjaxResult getInfo(@PathVariable("channelId") Integer channelId)
    {
        return success(tChannelRujinService.selectTChannelRujinById(channelId));
    }

    /**
     * 新增入金渠道
     */
    @PreAuthorize("@ss.hasPermi('business:rujin:add')")
    @Log(title = "入金渠道", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TChannelRujin tChannelRujin)
    {
        return toAjax(tChannelRujinService.insertTChannelRujin(tChannelRujin));
    }

    /**
     * 修改入金渠道
     */
    @PreAuthorize("@ss.hasPermi('business:rujin:edit')")
    @Log(title = "入金渠道", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TChannelRujin tChannelRujin)
    {
        return toAjax(tChannelRujinService.updateTChannelRujin(tChannelRujin));
    }

    /**
     * 删除入金渠道
     */
    @PreAuthorize("@ss.hasPermi('business:rujin:remove')")
    @Log(title = "入金渠道", businessType = BusinessType.DELETE)
	@DeleteMapping("/{channelIds}")
    public AjaxResult remove(@PathVariable Integer[] channelIds)
    {
        return toAjax(tChannelRujinService.deleteTChannelRujinByIds(channelIds));
    }
}
