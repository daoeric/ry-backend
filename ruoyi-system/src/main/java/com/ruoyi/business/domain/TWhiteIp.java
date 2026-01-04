package com.ruoyi.business.domain;

import java.math.BigDecimal;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 上游信息对象 t_white_ip
 * 
 * @author ruoyi
 * @date 2026-01-04
 */
@Data
public class TWhiteIp
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String alias;

    /** IP白名单 */
    @Excel(name = "IP白名单")
    private String ipAddress;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String code;

    /** 0-启用 1-禁用 */
    @Excel(name = "0-启用 1-禁用")
    private Integer status;

    /** 群组ID */
    @Excel(name = "群组ID")
    private String groupId;

    /** 上游机器人用户名 */
    @Excel(name = "上游机器人用户名")
    private String botUsername;

    /** 上游预付 */
    @Excel(name = "上游预付")
    private BigDecimal prepBalance;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setAlias(String alias) 
    {
        this.alias = alias;
    }

    public String getAlias() 
    {
        return alias;
    }
    public void setIpAddress(String ipAddress) 
    {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() 
    {
        return ipAddress;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setGroupId(String groupId) 
    {
        this.groupId = groupId;
    }

    public String getGroupId() 
    {
        return groupId;
    }
    public void setBotUsername(String botUsername) 
    {
        this.botUsername = botUsername;
    }

    public String getBotUsername() 
    {
        return botUsername;
    }
    public void setPrepBalance(BigDecimal prepBalance) 
    {
        this.prepBalance = prepBalance;
    }

    public BigDecimal getPrepBalance() 
    {
        return prepBalance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("alias", getAlias())
            .append("ipAddress", getIpAddress())
            .append("code", getCode())
            .append("status", getStatus())
            .append("groupId", getGroupId())
            .append("botUsername", getBotUsername())
            .append("prepBalance", getPrepBalance())
            .toString();
    }
}
