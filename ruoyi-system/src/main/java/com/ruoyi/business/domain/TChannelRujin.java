package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 入金渠道对象 t_channel_rujin
 * 
 * @author nice
 * @date 2024-04-20
 */
@Data
public class TChannelRujin
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type= IdType.AUTO)
    private Integer channelId;

    /** 上游商户号（对接用） */
    private String merchantId;

    /** 渠道名称 */
    private String name;

    /** 类code */
    private String code;

    /** 状态 */
    private Integer status;

    /** 秘钥 */
    private String priKey;

    /** 支付URL */
    private String apiUrl;

    /** 代收回调 */
    private String depositNotify;

    /** 通道成本费率 */
    private BigDecimal channelRate;

    /** 上游名 */
    private String alias;

    /** 产品编码 */
    private String productId;

    /** 最小限额 */
    private BigDecimal minAmount;

    /** 最大限额 */
    private BigDecimal maxAmount;

    /** 固定金额 */
    private String fixAmount;

    /** 通道编码 */
    private String channelType;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 备注 */
    private String remark;

    @TableField(exist = false)
    private String[] productIds;
}
