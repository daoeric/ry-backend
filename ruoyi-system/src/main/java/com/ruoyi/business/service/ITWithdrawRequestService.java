package com.ruoyi.business.service;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.business.domain.TWithdrawRequest;

/**
 * 提现订单Service接口
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
public interface ITWithdrawRequestService extends IService<TWithdrawRequest>
{
    /**
     * 查询提现订单
     * 
     * @param withdrawId 提现订单主键
     * @return 提现订单
     */
    public TWithdrawRequest selectTWithdrawRequestByWithdrawId(String withdrawId);

    /**
     * 查询提现订单列表
     * 
     * @param tWithdrawRequest 提现订单
     * @return 提现订单集合
     */
    public List<TWithdrawRequest> selectTWithdrawRequestList(TWithdrawRequest tWithdrawRequest);

    /**
     * 新增提现订单
     * 
     * @param tWithdrawRequest 提现订单
     * @return 结果
     */
    public int insertTWithdrawRequest(TWithdrawRequest tWithdrawRequest);

    /**
     * 修改提现订单
     * 
     * @param tWithdrawRequest 提现订单
     * @return 结果
     */
    public int updateTWithdrawRequest(TWithdrawRequest tWithdrawRequest);

    /**
     * 批量删除提现订单
     * 
     * @param withdrawIds 需要删除的提现订单主键集合
     * @return 结果
     */
    public int deleteTWithdrawRequestByWithdrawIds(String[] withdrawIds);

    /**
     * 删除提现订单信息
     * 
     * @param withdrawId 提现订单主键
     * @return 结果
     */
    public int deleteTWithdrawRequestByWithdrawId(String withdrawId);

    boolean withdraw(Long userId, BigDecimal withdrawAmount, Long bankInfoId);

    boolean approve(String withdrawId, Integer status,String remark);
}
