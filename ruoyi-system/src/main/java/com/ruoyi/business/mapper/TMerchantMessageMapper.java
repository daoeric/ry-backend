package com.ruoyi.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.business.domain.TMerchantMessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商户消息Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-20
 */
public interface TMerchantMessageMapper extends BaseMapper<TMerchantMessage>
{
    /**
     * 查询商户消息
     * 
     * @param id 商户消息主键
     * @return 商户消息
     */
    TMerchantMessage selectTMerchantMessageById(Long id);

    /**
     * 查询商户消息列表
     * 
     * @param tMerchantMessage 商户消息
     * @return 商户消息集合
     */
    List<TMerchantMessage> selectTMerchantMessageList(TMerchantMessage tMerchantMessage);

    /**
     * 新增商户消息
     * 
     * @param tMerchantMessage 商户消息
     * @return 结果
     */
    int insertTMerchantMessage(TMerchantMessage tMerchantMessage);

    /**
     * 修改商户消息
     * 
     * @param tMerchantMessage 商户消息
     * @return 结果
     */
    int updateTMerchantMessage(TMerchantMessage tMerchantMessage);

    /**
     * 删除商户消息
     * 
     * @param id 商户消息主键
     * @return 结果
     */
    int deleteTMerchantMessageById(Long id);

    /**
     * 批量删除商户消息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    int deleteTMerchantMessageByIds(Long[] ids);
    
    /**
     * 查询商户未读消息数量
     * 
     * @param customerId 商户ID
     * @return 未读消息数量
     */
    int selectUnreadCountByCustomerId(@Param("customerId") Long customerId);
    
    /**
     * 查询商户消息列表（分页）
     * 
     * @param customerId 商户ID
     * @param offset 偏移量
     * @param limit 条数
     * @return 商户消息集合
     */
    List<TMerchantMessage> selectMessagesByCustomerId(@Param("customerId") Long customerId, @Param("offset") int offset, @Param("limit") int limit);
    
    /**
     * 批量标记消息为已读
     * 
     * @param customerId 商户ID
     * @return 结果
     */
    int markAllAsRead(@Param("customerId") Long customerId);
}