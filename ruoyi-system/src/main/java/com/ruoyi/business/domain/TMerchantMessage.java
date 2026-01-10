package com.ruoyi.business.domain;

import com.baomidou.mybatisplus.annotation.TableField;
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

    /** 状态：0-未读，1-已读 */
    @TableField(exist = false)
    private Integer status;

    /** 消息类型：0-普通消息，1-奖励通知，2-系统通知 */
    @Excel(name = "消息类型：0-普通消息，1-奖励通知，2-系统通知")
    private Integer type;

    /** 已读商户ID列表，以逗号分隔 */
    @Excel(name = "已读商户ID列表")
    private String readMerchantIds;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 阅读时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date readTime;

    /**
     * 判断指定商户是否已读此消息
     * @param customerId 商户ID
     * @return 是否已读
     */
    public boolean isReadByCustomer(Long customerId) {
        if (readMerchantIds == null || readMerchantIds.trim().isEmpty() || customerId == null) {
            return false;
        }
        String[] ids = readMerchantIds.split(",");
        for (String id : ids) {
            if (id.trim().equals(customerId.toString())) {
                return true;
            }
        }
        return false;
    }
}