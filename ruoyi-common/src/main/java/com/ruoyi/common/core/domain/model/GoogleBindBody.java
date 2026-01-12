package com.ruoyi.common.core.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 用户登录对象
 * 
 * @author ruoyi
 */
@Data
public class GoogleBindBody
{
    @NotEmpty(message = "谷歌验证码不能为空")
    private String code;

    @NotEmpty(message = "密钥不能为空")
    private String secret;

    @NotEmpty(message = "用户名不能为空")
    private String username;
}
