package com.ruoyi.common.payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositResult {

    @JsonIgnore
    private int code;

    private String payUrl;
    @JsonIgnore
    private String msg;

    private String orderNo;

    private String requestId;

    private Long memberid;

    private BigDecimal amount;

    @JsonIgnore
    private String result;


    /**
     * 收款卡ID
     */
    @JsonIgnore
    private Long collectionId;

    @JsonIgnore
    private BigDecimal codeRate;

    @JsonIgnore
    private BigDecimal channelRate;


}
