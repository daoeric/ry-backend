package com.ruoyi.common.dto.merchant;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class WithdrawAddDto {

    @NotNull(message = "Withdraw amount cant be null")
    private BigDecimal withdrawAmount;

    @NotNull(message = "Bank cant not be null")
    private Long bankInfoId;



}
