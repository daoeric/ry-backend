package com.ruoyi.web.controller.monitor;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginMerchantUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.MerchantOnlineService;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.system.domain.SysUserOnline;

/**
 * 商户在线用户监控
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/monitor/merchantOnline")
public class MerchantOnlineController extends BaseController
{
    @Autowired
    private MerchantOnlineService merchantOnlineService;
    
    @Autowired
    private TokenService tokenService;

    @PreAuthorize("@ss.hasPermi('monitor:online:list')")
    @GetMapping("/list")
    public TableDataInfo list(String ipaddr, String userName)
    {
        startPage();
        List<SysUserOnline> list = merchantOnlineService.selectMerchantOnlineList(ipaddr, userName);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('monitor:online:forceLogout')")
    @Log(title = "商户在线用户", businessType = BusinessType.FORCE)
    @DeleteMapping("/{tokenId}")
    public AjaxResult forceLogout(@PathVariable String tokenId)
    {
        merchantOnlineService.forceMerchantLogout(tokenId);
        return success();
    }
    
    @PreAuthorize("@ss.hasPermi('monitor:online:list')")
    @GetMapping("/count")
    public AjaxResult getMerchantOnlineCount()
    {
        int count = merchantOnlineService.getMerchantOnlineCount();
        AjaxResult ajax = AjaxResult.success();
        ajax.put("count", count);
        return ajax;
    }
    
    /**
     * 获取商户在线状态
     */
    @GetMapping("/status")
    public AjaxResult getMerchantOnlineStatus(HttpServletRequest request, HttpServletResponse response)
    {
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(request);
        if (loginUser != null) {
            AjaxResult ajax = AjaxResult.success();
            ajax.put("isOnline", true);
            ajax.put("username", loginUser.getUsername());
            ajax.put("loginTime", loginUser.getLoginTime());
            ajax.put("ipaddr", loginUser.getIpaddr());
            ajax.put("browser", loginUser.getBrowser());
            ajax.put("os", loginUser.getOs());
            return ajax;
        } else {
            return AjaxResult.success("商户未在线");
        }
    }
}