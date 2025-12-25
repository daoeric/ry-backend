package com.ruoyi.common.enums;

/**
 *  资金操作
 * 
 * @author yf
 */
public enum BillOperateTypeEnum
{
    DEPOSIT(1,"Deposit"),WITHDRAWAL(2,"Withdraw"),MANUAL_IN(3,"Adjust Balance"),REJECT(5,"Reject Withdraw"),
    COMMISSION(6,"Scan Reward"),CONFIRM(7,"Confirm Withdraw")

    ;

    private final int code;
    private final String info;

    BillOperateTypeEnum(int code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public int getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
