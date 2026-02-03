package com.ruoyi.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RealNameAuthDto {

    /**
     * 真实姓名
     */
    @NotNull(message = "Real name cant be null")
    private String realName;

    @NotNull(message = "Phone number cant be null")
    private String phoneNumber;

    private String telegramId;

    private String whatsappId;


}