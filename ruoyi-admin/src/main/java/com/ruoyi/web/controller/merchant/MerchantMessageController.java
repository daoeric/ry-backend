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
        tMerchantMessage.setCustomerId(loginUser.getId());
        List<TMerchantMessage> list = merchantMessageService.selectTMerchantMessageList(tMerchantMessage);
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
        boolean result = merchantMessageService.markAsRead(messageId);
        return result ? AjaxResult.success("消息已标记为已读") : AjaxResult.error("标记失败");
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
        return result ? AjaxResult.success("所有消息已标记为已读") : AjaxResult.error("标记失败");
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
        if (message != null && (message.getCustomerId() == null || message.getCustomerId().equals(loginUser.getId()))) {
            // Mark as read if it's unread
            if (message.getStatus() == 0) {
                merchantMessageService.markAsRead(messageId);
            }
            return AjaxResult.success(message);
        } else {
            return AjaxResult.error("消息不存在或无权限查看");
        }
    }
}