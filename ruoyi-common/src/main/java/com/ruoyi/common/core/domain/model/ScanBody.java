package com.ruoyi.common.core.domain.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 用户登录对象
 * 
 * @author ruoyi
 */
@Data
public class ScanBody
{
    /**
     * 条形码
     */
    @NotBlank(message = "条形码不能为空")
    private String barcode;

    /**
     * 时间戳
     */
    @NotNull(message = "时间戳不能为空")
    private Long timestamp;

}
