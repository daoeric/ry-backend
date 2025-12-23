package com.ruoyi.business.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 商户消息对象 t_merchant_message
 * 
 * @author ruoyi
 * @date 2025-12-20
 */
@Data
public class TMerchantMessage
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 消息标题 */
    @Excel(name = "消息标题")
    private String title;

    /** 消息内容 */
    @Excel(name = "消息内容")
    private String content;

    /** 商户ID，NULL表示发送给所有商户 */
    @Excel(name = "商户ID")
    private Long customerId;

    /** 状态：0-未读，1-已读 */
    @Excel(name = "状态：0-未读，1-已读")
    private Integer status;

    /** 消息类型：0-普通消息，1-奖励通知，2-系统通知 */
    @Excel(name = "消息类型：0-普通消息，1-奖励通知，2-系统通知")
    private Integer type;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 阅读时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date readTime;
}