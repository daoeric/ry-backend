package com.ruoyi.common.vo.merchant;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class IndexVO {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 奖金池
     */
    private BigDecimal pool;
    /**
     * 今日已奖励
     */
    private BigDecimal totalRewards;
}
