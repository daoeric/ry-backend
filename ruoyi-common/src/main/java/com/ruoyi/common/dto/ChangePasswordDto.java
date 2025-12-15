package com.ruoyi.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ChangePasswordDto {

    /**
     * 旧密码
     */
    @NotBlank(message = "旧密码不能为空")
    @Size(min = 6, max = 20, message = "旧密码长度必须在6到20个字符之间")
    private String oldPassword;

    /**
     * 新密码
     */
    @NotBlank(message = "新密码不能为空")
    @Size(min = 6, max = 20, message = "新密码长度必须在6到20个字符之间")
    private String newPassword;

    /**
     * 确认密码
     */
    @NotBlank(message = "确认密码不能为空")
    @Size(min = 6, max = 20, message = "确认密码长度必须在6到20个字符之间")
    private String confirmNewPassword;

}