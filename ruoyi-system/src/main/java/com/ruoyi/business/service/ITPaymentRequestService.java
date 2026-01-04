package com.ruoyi.business.service;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.business.domain.TPaymentRequest;
import com.ruoyi.common.dto.payment.DepositDto;
import com.ruoyi.common.payment.DepositResult;

/**
 * 存入订单Service接口
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
public interface ITPaymentRequestService 
{
    /**
     * 查询存入订单
     * 
     * @param requestId 存入订单主键
     * @return 存入订单
     */
    public TPaymentRequest selectTPaymentRequestByRequestId(String requestId);

    /**
     * 查询存入订单列表
     * 
     * @param tPaymentRequest 存入订单
     * @return 存入订单集合
     */
    public List<TPaymentRequest> selectTPaymentRequestList(TPaymentRequest tPaymentRequest);

    /**
     * 新增存入订单
     * 
     * @param tPaymentRequest 存入订单
     * @return 结果
     */
    public int insertTPaymentRequest(TPaymentRequest tPaymentRequest);

    /**
     * 修改存入订单
     * 
     * @param tPaymentRequest 存入订单
     * @return 结果
     */
    public int updateTPaymentRequest(TPaymentRequest tPaymentRequest);

    /**
     * 批量删除存入订单
     * 
     * @param requestIds 需要删除的存入订单主键集合
     * @return 结果
     */
    public int deleteTPaymentRequestByRequestIds(String[] requestIds);

    /**
     * 删除存入订单信息
     * 
     * @param requestId 存入订单主键
     * @return 结果
     */
    public int deleteTPaymentRequestByRequestId(String requestId);

    DepositResult deposit(Long userId, String username, BigDecimal orderAmount);

    boolean approve(String requestId, BigDecimal realAmount, String remark);

    DepositResult pay(DepositDto depositDto);

    boolean doSuccess(String billNo, BigDecimal amount);
}
