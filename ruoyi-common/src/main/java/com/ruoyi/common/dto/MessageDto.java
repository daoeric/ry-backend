package com.ruoyi.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MessageDto {

    /**
     * 消息标题
     */
    @NotBlank(message = "消息标题不能为空")
    private String title;

    /**
     * 消息内容
     */
    @NotBlank(message = "消息内容不能为空")
    private String content;

    /**
     * 商户ID，null表示发送给所有商户
     */
    private Long customerId;

    /**
     * 消息类型：0-普通消息，1-奖励通知，2-系统通知
     */
    @NotNull(message = "消息类型不能为空")
    private Integer type;
}