package com.ruoyi.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.business.domain.TMerchantMessage;

import java.util.List;

/**
 * 商户消息Service接口
 * 
 * @author ruoyi
 * @date 2025-12-20
 */
public interface ITMerchantMessageService extends IService<TMerchantMessage>
{
    /**
     * 查询商户消息
     * 
     * @param id 商户消息主键
     * @return 商户消息
     */
    public TMerchantMessage selectTMerchantMessageById(Long id);

    /**
     * 查询商户消息列表
     * 
     * @param tMerchantMessage 商户消息
     * @return 商户消息集合
     */
    public List<TMerchantMessage> selectTMerchantMessageList(TMerchantMessage tMerchantMessage);

    /**
     * 新增商户消息
     * 
     * @param tMerchantMessage 商户消息
     * @return 结果
     */
    public int insertTMerchantMessage(TMerchantMessage tMerchantMessage);

    /**
     * 修改商户消息
     * 
     * @param tMerchantMessage 商户消息
     * @return 结果
     */
    public int updateTMerchantMessage(TMerchantMessage tMerchantMessage);

    /**
     * 批量删除商户消息
     * 
     * @param ids 需要删除的商户消息主键集合
     * @return 结果
     */
    public int deleteTMerchantMessageByIds(Long[] ids);

    /**
     * 删除商户消息信息
     * 
     * @param id 商户消息主键
     * @return 结果
     */
    public int deleteTMerchantMessageById(Long id);
    
    /**
     * 查询商户未读消息数量
     * 
     * @param customerId 商户ID
     * @return 未读消息数量
     */
    public int selectUnreadCountByCustomerId(Long customerId);
    
    /**
     * 发送消息给指定商户
     * 
     * @param title 消息标题
     * @param content 消息内容
     * @param customerId 商户ID
     * @param type 消息类型
     * @return 结果
     */
    public boolean sendMessageToMerchant(String title, String content, Long customerId, Integer type);
    
    /**
     * 发送消息给所有商户
     * 
     * @param title 消息标题
     * @param content 消息内容
     * @param type 消息类型
     * @return 结果
     */
    public boolean sendMessageToAllMerchants(String title, String content, Integer type);
    
    /**
     * 查询商户消息列表（分页）
     * 
     * @param customerId 商户ID
     * @param pageNum 页码
     * @param pageSize 每页条数
     * @return 商户消息集合
     */
    public List<TMerchantMessage> selectMessagesByCustomerId(Long customerId, int pageNum, int pageSize);
    
    /**
     * 标记消息为已读
     * 
     * @param messageId 消息ID
     * @return 结果
     */
    public boolean markAsRead(Long messageId);
    
    /**
     * 批量标记消息为已读
     * 
     * @param customerId 商户ID
     * @return 结果
     */
    public boolean markAllAsRead(Long customerId);
    
    /**
     * 发送奖励通知
     * 
     * @param customerId 商户ID
     * @param amount 奖励金额
     * @return 结果
     */
    public boolean sendRewardNotification(Long customerId, java.math.BigDecimal amount);
}