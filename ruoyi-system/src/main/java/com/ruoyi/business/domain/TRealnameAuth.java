package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 实名认证对象 t_realname_auth
 * 
 * @author ruoyi
 * @date 2025-12-15
 */
@Data
public class TRealnameAuth
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 商户ID */
    @Excel(name = "商户ID")
    private Long customerId;

    /** 真实姓名 */
    @Excel(name = "真实姓名")
    private String realName;

//    /** 身份证正面图片URL */
//    @Excel(name = "身份证正面图片URL")
//    private String idCardFront;
//
//    /** 身份证反面图片URL */
//    @Excel(name = "身份证反面图片URL")
//    private String idCardBack;

    private String phoneNumber;

    private String telegramId;

    private String whatsappId;


    /** 状态：0-待审核，1-审核通过，2-审核拒绝 */
    @Excel(name = "状态：0-待审核，1-审核通过，2-审核拒绝")
    private Integer status;

    /** 审核原因 */
    @Excel(name = "审核原因")
    private String auditReason;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date auditTime;

    /** 审核人 */
    @Excel(name = "审核人")
    private String auditBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}