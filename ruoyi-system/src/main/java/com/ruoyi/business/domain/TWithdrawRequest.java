package com.ruoyi.business.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 提现订单对象 t_withdraw_request
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
public class TWithdrawRequest extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String withdrawId;

    /** 提现金额 */
    @Excel(name = "提现金额")
    private BigDecimal withdrawAmount;

    /** 商户号 */
    @Excel(name = "商户号")
    private Long customerId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String username;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 真实金额 */
    @Excel(name = "真实金额")
    private BigDecimal realAmount;

    public void setWithdrawId(String withdrawId) 
    {
        this.withdrawId = withdrawId;
    }

    public String getWithdrawId() 
    {
        return withdrawId;
    }
    public void setWithdrawAmount(BigDecimal withdrawAmount) 
    {
        this.withdrawAmount = withdrawAmount;
    }

    public BigDecimal getWithdrawAmount() 
    {
        return withdrawAmount;
    }
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setRealAmount(BigDecimal realAmount) 
    {
        this.realAmount = realAmount;
    }

    public BigDecimal getRealAmount() 
    {
        return realAmount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("withdrawId", getWithdrawId())
            .append("withdrawAmount", getWithdrawAmount())
            .append("customerId", getCustomerId())
            .append("username", getUsername())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("realAmount", getRealAmount())
            .append("remark", getRemark())
            .toString();
    }
}
