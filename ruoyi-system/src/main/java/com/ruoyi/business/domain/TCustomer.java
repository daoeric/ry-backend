package com.ruoyi.business.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户管理对象 t_customer
 * 
 * @author ruoyi
 * @date 2025-12-08
 */
@Data
public class TCustomer
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long id;

    /** 用户名 */
    @Excel(name = "用户名")
    private String username;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 邀请码 */
    @Excel(name = "邀请码")
    private String inviteCode;

    /** path */
    @Excel(name = "path")
    private String path;

    /** 父级ID */
    @Excel(name = "父级ID")
    private Long pId;

    /** 余额 */
    @Excel(name = "余额")
    private BigDecimal balance;

    /** 冻结余额 */
    @Excel(name = "冻结余额")
    private BigDecimal lockBalance;

    /** VIP等级 */
    @Excel(name = "VIP等级")
    private Integer grade;

    /** 提款密码 */
    @Excel(name = "提款密码")
    private String withdrawPassword;

    /** 最后登录IP */
    @Excel(name = "最后登录IP")
    private String lastLoginAddress;

    /** 最后登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后登录时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastLoginTime;

    /** 0正常 1启用 */
    @Excel(name = "0正常 1启用")
    private Integer status;

    /** 实名认证状态：0-未认证，1-待审核，2-已认证 */
    @Excel(name = "实名认证状态：0-未认证，1-待审核，2-已认证")
    private Integer realnameStatus;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 备注 */
//    private String remark;

    /**
     * VIP过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date expireTime;

    /**
     * 奖励次数
     */
    private Integer scanCount;

    /**
     * 充值金额，VIP晋升
     */
    private BigDecimal depositAmount;

}