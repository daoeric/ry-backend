package com.ruoyi.business.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * VIP管理对象 t_vip
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
@Data
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

    /**
     * 存款条件，达到多少可以自动升级
     */
    private BigDecimal depositCondition;

}
