package com.ruoyi.common.dto.payment;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 代付dto
 */
@Data
public class DepositDto implements Serializable {

    @NotEmpty(message = "支付通道不能为空")
    private String pay_bankcode;

    /**
     * 订单金额
     */
    @NotNull(message = "订单金额不能为空")
    private BigDecimal pay_amount;

    private Long userId;



}
