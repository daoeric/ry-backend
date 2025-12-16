package com.ruoyi.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RealNameAuthDto {

    /**
     * 真实姓名
     */
    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    /**
     * 身份证正面图片URL
     */
    @NotBlank(message = "身份证正面图片不能为空")
    private String idCardFront;

    /**
     * 身份证反面图片URL
     */
    @NotBlank(message = "身份证反面图片不能为空")
    private String idCardBack;
}