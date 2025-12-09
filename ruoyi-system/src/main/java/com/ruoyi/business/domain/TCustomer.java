package com.ruoyi.business.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户管理对象 t_customer
 * 
 * @author ruoyi
 * @date 2025-12-08
 */
public class TCustomer extends BaseEntity
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

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setInviteCode(String inviteCode) 
    {
        this.inviteCode = inviteCode;
    }

    public String getInviteCode() 
    {
        return inviteCode;
    }
    public void setPath(String path) 
    {
        this.path = path;
    }

    public String getPath() 
    {
        return path;
    }
    public void setpId(Long pId) 
    {
        this.pId = pId;
    }

    public Long getpId() 
    {
        return pId;
    }
    public void setBalance(BigDecimal balance) 
    {
        this.balance = balance;
    }

    public BigDecimal getBalance() 
    {
        return balance;
    }
    public void setLockBalance(BigDecimal lockBalance) 
    {
        this.lockBalance = lockBalance;
    }

    public BigDecimal getLockBalance() 
    {
        return lockBalance;
    }
    public void setGrade(Integer grade) 
    {
        this.grade = grade;
    }

    public Integer getGrade() 
    {
        return grade;
    }
    public void setWithdrawPassword(String withdrawPassword) 
    {
        this.withdrawPassword = withdrawPassword;
    }

    public String getWithdrawPassword() 
    {
        return withdrawPassword;
    }
    public void setLastLoginAddress(String lastLoginAddress) 
    {
        this.lastLoginAddress = lastLoginAddress;
    }

    public String getLastLoginAddress() 
    {
        return lastLoginAddress;
    }
    public void setLastLoginTime(Date lastLoginTime) 
    {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getLastLoginTime() 
    {
        return lastLoginTime;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("inviteCode", getInviteCode())
            .append("path", getPath())
            .append("pId", getpId())
            .append("balance", getBalance())
            .append("lockBalance", getLockBalance())
            .append("grade", getGrade())
            .append("withdrawPassword", getWithdrawPassword())
            .append("lastLoginAddress", getLastLoginAddress())
            .append("lastLoginTime", getLastLoginTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .toString();
    }
}
