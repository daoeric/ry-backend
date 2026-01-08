package com.ruoyi.business.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.business.domain.TCustomer;
import com.ruoyi.common.enums.BillOperateTypeEnum;

/**
 * 用户管理Service接口
 * 
 * @author ruoyi
 * @date 2025-12-08
 */
public interface ITCustomerService extends IService<TCustomer>
{
    /**
     * 查询用户管理
     * 
     * @param id 用户管理主键
     * @return 用户管理
     */
    public TCustomer selectTCustomerById(Long id);

    /**
     * 查询用户管理列表
     * 
     * @param tCustomer 用户管理
     * @return 用户管理集合
     */
    public List<TCustomer> selectTCustomerList(TCustomer tCustomer);

    /**
     * 新增用户管理
     * 
     * @param tCustomer 用户管理
     * @return 结果
     */
    public int insertTCustomer(TCustomer tCustomer);

    /**
     * 检查邀请码是否已存在
     * 
     * @param inviteCode 邀请码
     * @return 是否存在
     */
    boolean existsByInviteCode(String inviteCode);

    /**
     * 修改用户管理
     * 
     * @param tCustomer 用户管理
     * @return 结果
     */
    public int updateTCustomer(TCustomer tCustomer);

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的用户管理主键集合
     * @return 结果
     */
    public int deleteTCustomerByIds(Long[] ids);

    /**
     * 删除用户管理信息
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    public int deleteTCustomerById(Long id);

    TCustomer selectTCustomerByUsername(String username);

    TCustomer selectTCustomerByInviteCode(String inviteCode);

    boolean changeBalance(Long userId, BigDecimal rewardAmount, BillOperateTypeEnum billOperateTypeEnum, String orderNo, String remark);
    
    boolean updatePwd(Long id, String newPassword);
    
    /**
     * 更新用户实名认证状态
     * 
     * @param id 用户ID
     * @param realnameStatus 实名认证状态
     * @return 结果
     */
    boolean updateRealnameStatus(Long id, Integer realnameStatus);
    
    /**
     * 查询所有商户ID
     * 
     * @return 商户ID列表
     */
    List<Long> selectAllCustomerIds();
    
    /**
     * 发送奖励通知
     * 
     * @param customerId 商户ID
     * @param amount 奖励金额
     * @param merchantMessageService 消息服务
     * @return 结果
     */
    boolean sendRewardNotification(Long customerId, java.math.BigDecimal amount, ITMerchantMessageService merchantMessageService);

    boolean renew(Long userId, Date expireTime);

    boolean useScanCount(Long userId);

    boolean register(TCustomer customer, TCustomer existInviteCustomer);
}