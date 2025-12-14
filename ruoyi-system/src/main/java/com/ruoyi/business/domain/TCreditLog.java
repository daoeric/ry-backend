package com.ruoyi.business.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 额度变更对象 t_credit_log
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
public class TCreditLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 商户号 */
    @Excel(name = "商户号")
    private Long customerId;

    /** 操作类型 */
    @Excel(name = "操作类型")
    private Integer opearteType;

    /** 操作金额，可以为负数 */
    @Excel(name = "操作金额，可以为负数")
    private BigDecimal opearteAmount;

    /** 操作前金额 */
    @Excel(name = "操作前金额")
    private BigDecimal preBalance;

    /** 操作后金额 */
    @Excel(name = "操作后金额")
    private BigDecimal postBalance;

    /** 关联ID */
    @Excel(name = "关联ID")
    private String refId;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCustomerId(Long customerId) 
    {
        this.customerId = customerId;
    }

    public Long getCustomerId() 
    {
        return customerId;
    }
    public void setOpearteType(Integer opearteType) 
    {
        this.opearteType = opearteType;
    }

    public Integer getOpearteType() 
    {
        return opearteType;
    }
    public void setOpearteAmount(BigDecimal opearteAmount) 
    {
        this.opearteAmount = opearteAmount;
    }

    public BigDecimal getOpearteAmount() 
    {
        return opearteAmount;
    }
    public void setPreBalance(BigDecimal preBalance) 
    {
        this.preBalance = preBalance;
    }

    public BigDecimal getPreBalance() 
    {
        return preBalance;
    }
    public void setPostBalance(BigDecimal postBalance) 
    {
        this.postBalance = postBalance;
    }

    public BigDecimal getPostBalance() 
    {
        return postBalance;
    }
    public void setRefId(String refId) 
    {
        this.refId = refId;
    }

    public String getRefId() 
    {
        return refId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("customerId", getCustomerId())
            .append("opearteType", getOpearteType())
            .append("opearteAmount", getOpearteAmount())
            .append("preBalance", getPreBalance())
            .append("postBalance", getPostBalance())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("refId", getRefId())
            .append("remark", getRemark())
            .toString();
    }
}
