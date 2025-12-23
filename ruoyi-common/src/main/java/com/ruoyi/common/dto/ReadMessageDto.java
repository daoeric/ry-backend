package com.ruoyi.common.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReadMessageDto {

    /**
     * 消息ID
     */
    @NotNull(message = "消息ID不能为空")
    private Long messageId;
}