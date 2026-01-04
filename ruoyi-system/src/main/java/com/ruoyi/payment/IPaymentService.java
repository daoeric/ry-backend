package com.ruoyi.payment;

import com.ruoyi.business.domain.TChannelRujin;
import com.ruoyi.business.domain.TWithdrawRequest;
import com.ruoyi.common.dto.payment.DepositDto;
import com.ruoyi.common.payment.DepositResult;

import java.math.BigDecimal;
import java.util.Map;

public interface IPaymentService {

    boolean queryOrderStatus(TWithdrawRequest withdrawRequest);

    /**
     * 代付
     * @param dto
     * @param requestId
     * @throws Exception
     */
//    WithdrawalResult withdrawal(WithdrawalDto dto, String requestId) throws Exception;


    /**
     * 代收
     *
     * @param depositDto
     * @param requestId
     * @param chanel
     * @return
     */
    DepositResult deposit(DepositDto depositDto, String requestId, TChannelRujin channel);

    /**
     * 代收回调
     * @param parameterMap
     * @return
     */
    boolean depositCallback(Map<String, Object> parameterMap) throws Exception;

    /**
     * 代付回调
     * @param parameterMap
     * @return  1- 回调失败  2-订单成功  3-订单失败
     */
//    WithdrawalCallbackResult withdrawalCallback(Map<String, Object> parameterMap)throws Exception;

    /**
     * 根据参数获取对应通道的订单号
     * @param parameterMap
     * @return
     */
    String getOrderNo(Map<String, Object> parameterMap);

    BigDecimal getRealAmount(Map<String, Object> parameterMap);

    /**
     * 获取通道回调正常时返回上游的字符串
     * @return
     */
    String getSuccessMsg();

}
