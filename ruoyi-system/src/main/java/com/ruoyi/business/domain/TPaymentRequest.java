package com.ruoyi.business.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 存入订单对象 t_payment_request
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
@Data
public class TPaymentRequest extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String requestId;

    /** 商户号 */
    @Excel(name = "商户号")
    private Long customerId;

    /** 商户名 */
    @Excel(name = "商户名")
    private String username;

    /** 支付状态 */
    @Excel(name = "支付状态")
    private Integer status;

    /** 订单金额 */
    @Excel(name = "订单金额")
    private BigDecimal orderAmount;

    /** 真实金额 */
    @Excel(name = "真实金额")
    private BigDecimal realAmount;

    /** 成功时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "成功时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date successTime;

}
