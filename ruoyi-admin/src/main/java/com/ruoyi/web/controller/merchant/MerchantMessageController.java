package com.ruoyi.web.controller.merchant;

import com.ruoyi.business.domain.TMerchantMessage;
import com.ruoyi.business.service.ITMerchantMessageService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginMerchantUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 商户消息Controller
 * 
 * @author ruoyi
 * @date 2025-12-20
 */
@RestController
@RequestMapping("/merchant/message")
public class MerchantMessageController extends BaseController
{
    @Autowired
    private ITMerchantMessageService merchantMessageService;
    
    @Autowired
    private TokenService tokenService;

    /**
     * 查询商户消息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(TMerchantMessage tMerchantMessage)
    {
        startPage();
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
//        tMerchantMessage.setCustomerId(loginUser.getId());
        List<TMerchantMessage> list = merchantMessageService.selectTMerchantMessageList(tMerchantMessage);
        for (TMerchantMessage merchantMessage : list) {
            merchantMessage.setStatus(merchantMessage.isReadByCustomer(loginUser.getId()) ? 1 : 0);
        }
        return getDataTable(list);
    }

    /**
     * 获取商户未读消息数量
     */
    @GetMapping("/unreadCount")
    public AjaxResult getUnreadCount()
    {
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        int unreadCount = merchantMessageService.selectUnreadCountByCustomerId(loginUser.getId());
        AjaxResult ajax = AjaxResult.success();
        ajax.put("unreadCount", unreadCount);
        return ajax;
    }

    /**
     * 标记消息为已读
     */
    @Log(title = "商户消息", businessType = BusinessType.UPDATE)
    @PutMapping("/read/{messageId}")
    public AjaxResult readMessage(@PathVariable("messageId") Long messageId)
    {
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        boolean result = merchantMessageService.markAsRead(messageId, loginUser.getId());
        return result ? AjaxResult.successByCode("merchant.message.read.success") : AjaxResult.errorByCode("merchant.message.read.error");
    }

    /**
     * 批量标记消息为已读
     */
    @Log(title = "商户消息", businessType = BusinessType.UPDATE)
    @PutMapping("/readAll")
    public AjaxResult readAllMessages()
    {
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        boolean result = merchantMessageService.markAllAsRead(loginUser.getId());
        return result ? AjaxResult.successByCode("merchant.message.read.all.success") : AjaxResult.errorByCode("merchant.message.read.all.error");
    }
    
    /**
     * 获取消息详情
     */
    @GetMapping("/{messageId}")
    public AjaxResult getMessageDetail(@PathVariable("messageId") Long messageId)
    {
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        TMerchantMessage message = merchantMessageService.selectTMerchantMessageById(messageId);
        
        // Check if message belongs to the current user or is a broadcast message
        if (message != null) {
            // Mark as read if it's unread
            if (!message.isReadByCustomer(loginUser.getId())) {
                merchantMessageService.markAsRead(messageId, loginUser.getId());
            }
            return AjaxResult.success(message);
        } else {
            return AjaxResult.errorByCode("user.not.exists");
        }
    }
}