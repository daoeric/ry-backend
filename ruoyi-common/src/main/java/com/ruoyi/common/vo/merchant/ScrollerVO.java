package com.ruoyi.common.vo.merchant;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ScrollerVO {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 奖金
     */
    private BigDecimal rewards;
}
