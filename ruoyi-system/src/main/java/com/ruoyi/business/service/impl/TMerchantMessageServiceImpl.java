package com.ruoyi.business.service.impl;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.TMerchantMessageMapper;
import com.ruoyi.business.domain.TMerchantMessage;
import com.ruoyi.business.service.ITMerchantMessageService;
import com.ruoyi.common.utils.DateUtils;

/**
 * 商户消息Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-20
 */
@Service
public class TMerchantMessageServiceImpl extends ServiceImpl<TMerchantMessageMapper, TMerchantMessage> implements ITMerchantMessageService
{
    @Autowired
    private TMerchantMessageMapper tMerchantMessageMapper;

    /**
     * 查询商户消息
     * 
     * @param id 商户消息主键
     * @return 商户消息
     */
    @Override
    public TMerchantMessage selectTMerchantMessageById(Long id)
    {
        return tMerchantMessageMapper.selectTMerchantMessageById(id);
    }

    /**
     * 查询商户消息列表
     * 
     * @param tMerchantMessage 商户消息
     * @return 商户消息
     */
    @Override
    public List<TMerchantMessage> selectTMerchantMessageList(TMerchantMessage tMerchantMessage)
    {
        return tMerchantMessageMapper.selectTMerchantMessageList(tMerchantMessage);
    }

    /**
     * 新增商户消息
     * 
     * @param tMerchantMessage 商户消息
     * @return 结果
     */
    @Override
    public int insertTMerchantMessage(TMerchantMessage tMerchantMessage)
    {
        tMerchantMessage.setCreateTime(DateUtils.getNowDate());
        return tMerchantMessageMapper.insertTMerchantMessage(tMerchantMessage);
    }

    /**
     * 修改商户消息
     * 
     * @param tMerchantMessage 商户消息
     * @return 结果
     */
    @Override
    public int updateTMerchantMessage(TMerchantMessage tMerchantMessage)
    {
        return tMerchantMessageMapper.updateTMerchantMessage(tMerchantMessage);
    }

    /**
     * 批量删除商户消息
     * 
     * @param ids 需要删除的商户消息主键
     * @return 结果
     */
    @Override
    public int deleteTMerchantMessageByIds(Long[] ids)
    {
        return tMerchantMessageMapper.deleteTMerchantMessageByIds(ids);
    }

    /**
     * 删除商户消息信息
     * 
     * @param id 商户消息主键
     * @return 结果
     */
    @Override
    public int deleteTMerchantMessageById(Long id)
    {
        return tMerchantMessageMapper.deleteTMerchantMessageById(id);
    }
    
    /**
     * 查询商户未读消息数量
     * 
     * @param customerId 商户ID
     * @return 未读消息数量
     */
    @Override
    public int selectUnreadCountByCustomerId(Long customerId)
    {
        return tMerchantMessageMapper.selectUnreadCountByCustomerId(customerId);
    }
    
    /**
     * 发送消息给指定商户
     * 
     * @param title 消息标题
     * @param content 消息内容
     * @param customerId 商户ID
     * @param type 消息类型
     * @return 结果
     */
    @Override
    public boolean sendMessageToMerchant(String title, String content, Long customerId, Integer type)
    {
        TMerchantMessage message = new TMerchantMessage();
        message.setTitle(title);
        message.setContent(content);
//        message.setCustomerId(customerId);
        message.setType(type);
        return this.save(message);
    }
    
    /**
     * 发送消息给所有商户
     * 
     * @param title 消息标题
     * @param content 消息内容
     * @param type 消息类型
     * @return 结果
     */
    @Override
    public boolean sendMessageToAllMerchants(String title, String content, Integer type)
    {
        TMerchantMessage message = new TMerchantMessage();
        message.setTitle(title);
        message.setContent(content);
//        message.setCustomerId(null); // 发送给所有商户
        message.setType(type);
        return this.save(message);
    }
    
    /**
     * 查询商户消息列表（分页）
     * 
     * @param customerId 商户ID
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return 商户消息集合
     */
    @Override
    public List<TMerchantMessage> selectMessagesByCustomerId(Long customerId, int pageNum, int pageSize)
    {
        int offset = (pageNum - 1) * pageSize;
        return tMerchantMessageMapper.selectMessagesByCustomerId(customerId, offset, pageSize);
    }
    
    /**
     * 标记消息为已读
     * 
     * @param messageId 消息ID
     * @return 结果
     */
    @Override
    public boolean markAsRead(Long messageId, Long customerId)
    {
        TMerchantMessage message = this.getById(messageId);
        if (message == null) {
            return false;
        }
        
        String readIds = message.getReadMerchantIds();
        String customerIdStr = String.valueOf(customerId);
        
        if (readIds == null || readIds.trim().isEmpty()) {
            readIds = customerIdStr;
        } else if (!readIds.contains(customerIdStr)) {
            readIds = readIds + "," + customerIdStr;
        } else {
            // 如果已经包含该商户ID，则直接返回成功
            return true;
        }
        
        message.setReadMerchantIds(readIds);
        message.setReadTime(new Date());
        
        return this.updateById(message);
    }
    
    /**
     * 批量标记消息为已读
     * 
     * @param customerId 商户ID
     * @return 结果
     */
    @Override
    public boolean markAllAsRead(Long customerId)
    {
        int result = tMerchantMessageMapper.markAllAsRead(customerId);
        return result >= 0;
    }
    
    /**
     * 发送奖励通知
     * 
     * @param customerId 商户ID
     * @param amount 奖励金额
     * @return 结果
     */
    @Override
    public boolean sendRewardNotification(Long customerId, java.math.BigDecimal amount)
    {
        String title = "奖励到账通知";
        String content = "恭喜您获得奖励 " + amount + " 元，奖励已发放到您的账户余额中。";
        return sendMessageToMerchant(title, content, customerId, 1); // 1 for reward notification
    }
}