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
 * 扫描订单对象 t_scan_order
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
@Data
public class TScanOrder
{
    private static final long serialVersionUID = 1L;

    /** 订单号 */
    private String orderNo;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long customerId;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** 条形码 */
    @Excel(name = "条形码")
    private String barcode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal rewardAmount;

    /** 0正常 1启用 */
    @Excel(name = "0正常 1启用")
    private Integer status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

}
