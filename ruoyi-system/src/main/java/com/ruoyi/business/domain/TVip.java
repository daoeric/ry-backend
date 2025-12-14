package com.ruoyi.business.domain;

import java.math.BigDecimal;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * VIP管理对象 t_vip
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
public class TVip extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 扫码次数 */
    @Excel(name = "扫码次数")
    private Integer scanLimit;

    /** 提现次数 */
    @Excel(name = "提现次数")
    private Integer withdrawLimit;

    /** 最小奖励 */
    @Excel(name = "最小奖励")
    private BigDecimal minReward;

    /** 最大奖励 */
    @Excel(name = "最大奖励")
    private BigDecimal maxReward;

    /** 分享奖励扫码次数 */
    @Excel(name = "分享奖励扫码次数")
    private Integer shareCount;

    /** 分享奖励 */
    @Excel(name = "分享奖励")
    private BigDecimal shareReward;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setScanLimit(Integer scanLimit) 
    {
        this.scanLimit = scanLimit;
    }

    public Integer getScanLimit() 
    {
        return scanLimit;
    }
    public void setWithdrawLimit(Integer withdrawLimit) 
    {
        this.withdrawLimit = withdrawLimit;
    }

    public Integer getWithdrawLimit() 
    {
        return withdrawLimit;
    }
    public void setMinReward(BigDecimal minReward) 
    {
        this.minReward = minReward;
    }

    public BigDecimal getMinReward() 
    {
        return minReward;
    }
    public void setMaxReward(BigDecimal maxReward) 
    {
        this.maxReward = maxReward;
    }

    public BigDecimal getMaxReward() 
    {
        return maxReward;
    }
    public void setShareCount(Integer shareCount) 
    {
        this.shareCount = shareCount;
    }

    public Integer getShareCount() 
    {
        return shareCount;
    }
    public void setShareReward(BigDecimal shareReward) 
    {
        this.shareReward = shareReward;
    }

    public BigDecimal getShareReward() 
    {
        return shareReward;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("scanLimit", getScanLimit())
            .append("withdrawLimit", getWithdrawLimit())
            .append("minReward", getMinReward())
            .append("maxReward", getMaxReward())
            .append("shareCount", getShareCount())
            .append("shareReward", getShareReward())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .toString();
    }
}
