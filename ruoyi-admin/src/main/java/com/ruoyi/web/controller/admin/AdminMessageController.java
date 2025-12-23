package com.ruoyi.web.controller.admin;

import com.ruoyi.business.service.ITCustomerService;
import com.ruoyi.business.service.ITMerchantMessageService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.dto.MessageDto;
import com.ruoyi.common.enums.BusinessType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 管理员消息Controller
 * 
 * @author ruoyi
 * @date 2025-12-20
 */
@RestController
@RequestMapping("/admin/message")
public class AdminMessageController extends BaseController
{
    @Autowired
    private ITMerchantMessageService merchantMessageService;
    
    @Autowired
    private ITCustomerService customerService;

    /**
     * 发送消息
     */
    @PreAuthorize("@ss.hasPermi('admin:message:send')")
    @Log(title = "发送消息", businessType = BusinessType.INSERT)
    @PostMapping("/send")
    public AjaxResult sendMessage(@Validated @RequestBody MessageDto messageDto)
    {
        boolean result;
        if (messageDto.getCustomerId() == null) {
            // 发送给所有商户
            List<Long> customerIds = customerService.selectAllCustomerIds();
            result = true;
            for (Long customerId : customerIds) {
                boolean singleResult = merchantMessageService.sendMessageToMerchant(
                    messageDto.getTitle(), 
                    messageDto.getContent(), 
                    customerId, 
                    messageDto.getType()
                );
                if (!singleResult) {
                    result = false;
                }
            }
        } else {
            // 发送给指定商户
            result = merchantMessageService.sendMessageToMerchant(
                messageDto.getTitle(), 
                messageDto.getContent(), 
                messageDto.getCustomerId(), 
                messageDto.getType()
            );
        }
        
        return result ? AjaxResult.successByCode("merchant.message.send.success") : AjaxResult.errorByCode("merchant.message.send.error");
    }
}