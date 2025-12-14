package com.ruoyi.common.vo.merchant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CustomerVO {

    private Long id;

    /** 用户名 */
    private String username;

    /** 邀请码 */
    private String inviteCode;

    /** path */
    private String path;

    /** 父级ID */
    private Long pId;

    private BigDecimal balance;

    private BigDecimal lockBalance;

    /** VIP等级 */
    @Excel(name = "VIP等级")
    private Integer grade;

    /** 最后登录IP */
    private String lastLoginAddress;

    /** 最后登录时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date lastLoginTime;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
//    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
