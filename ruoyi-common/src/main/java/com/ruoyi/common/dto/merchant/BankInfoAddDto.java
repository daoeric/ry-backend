package com.ruoyi.common.dto.merchant;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class BankInfoAddDto {

    @NotEmpty(message = "Bank Name cant be null")
    private String bankName;

    @NotEmpty(message = "Bank Card cant be null")
    private String cardNumber;

}
