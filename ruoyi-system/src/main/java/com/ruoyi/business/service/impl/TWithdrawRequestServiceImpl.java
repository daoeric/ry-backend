package com.ruoyi.business.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.business.domain.TBankInfo;
import com.ruoyi.business.domain.TCustomer;
import com.ruoyi.business.service.ITBankInfoService;
import com.ruoyi.business.service.ITCustomerService;
import com.ruoyi.common.enums.BillOperateTypeEnum;
import com.ruoyi.common.enums.OrderEnum;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.SnowflakeKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.TWithdrawRequestMapper;
import com.ruoyi.business.domain.TWithdrawRequest;
import com.ruoyi.business.service.ITWithdrawRequestService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 提现订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
@Service
public class TWithdrawRequestServiceImpl extends ServiceImpl<TWithdrawRequestMapper,TWithdrawRequest> implements ITWithdrawRequestService
{
    @Autowired
    private TWithdrawRequestMapper tWithdrawRequestMapper;

    @Autowired
    private ITCustomerService customerService;

    @Autowired
    private ITBankInfoService bankInfoService;

    @Autowired
    private SnowflakeKeyGenerator snowflakeKeyGenerator;

    /**
     * 查询提现订单
     * 
     * @param withdrawId 提现订单主键
     * @return 提现订单
     */
    @Override
    public TWithdrawRequest selectTWithdrawRequestByWithdrawId(String withdrawId)
    {
        return tWithdrawRequestMapper.selectTWithdrawRequestByWithdrawId(withdrawId);
    }

    /**
     * 查询提现订单列表
     * 
     * @param tWithdrawRequest 提现订单
     * @return 提现订单
     */
    @Override
    public List<TWithdrawRequest> selectTWithdrawRequestList(TWithdrawRequest tWithdrawRequest)
    {
        return tWithdrawRequestMapper.selectTWithdrawRequestList(tWithdrawRequest);
    }

    /**
     * 新增提现订单
     * 
     * @param tWithdrawRequest 提现订单
     * @return 结果
     */
    @Override
    public int insertTWithdrawRequest(TWithdrawRequest tWithdrawRequest)
    {
        tWithdrawRequest.setCreateTime(DateUtils.getNowDate());
        return tWithdrawRequestMapper.insertTWithdrawRequest(tWithdrawRequest);
    }

    /**
     * 修改提现订单
     * 
     * @param tWithdrawRequest 提现订单
     * @return 结果
     */
    @Override
    public int updateTWithdrawRequest(TWithdrawRequest tWithdrawRequest)
    {
        tWithdrawRequest.setUpdateTime(DateUtils.getNowDate());
        return tWithdrawRequestMapper.updateTWithdrawRequest(tWithdrawRequest);
    }

    /**
     * 批量删除提现订单
     * 
     * @param withdrawIds 需要删除的提现订单主键
     * @return 结果
     */
    @Override
    public int deleteTWithdrawRequestByWithdrawIds(String[] withdrawIds)
    {
        return tWithdrawRequestMapper.deleteTWithdrawRequestByWithdrawIds(withdrawIds);
    }

    /**
     * 删除提现订单信息
     * 
     * @param withdrawId 提现订单主键
     * @return 结果
     */
    @Override
    public int deleteTWithdrawRequestByWithdrawId(String withdrawId)
    {
        return tWithdrawRequestMapper.deleteTWithdrawRequestByWithdrawId(withdrawId);
    }

    @Override
    @Transactional
    public boolean withdraw(Long userId, BigDecimal withdrawAmount, Long bankInfoId) {
        //查看余额是否足够
        TCustomer customer = customerService.getById(userId);
        BigDecimal balance = customer.getBalance();
        if (withdrawAmount.compareTo(balance)>0) {
            throw new CustomException("余额不足！");
        }
        //查看银行卡信息
        TBankInfo bankInfo = bankInfoService.getById(bankInfoId);
        if (bankInfo == null) {
            throw new CustomException("收款信息不存在！");
        }
        String bankName = bankInfo.getBankName();
        String cardNumber = bankInfo.getBankCard();
        String realName = customer.getRealName();
        TWithdrawRequest withdrawRequest = new TWithdrawRequest();
        String withdrawId = "WT"+snowflakeKeyGenerator.generateKey().toString();
        withdrawRequest.setCustomerId(userId);
        withdrawRequest.setWithdrawAmount(withdrawAmount);
        withdrawRequest.setUsername(customer.getUsername());
        withdrawRequest.setBankName(bankName);
        withdrawRequest.setBankNo(cardNumber);
        withdrawRequest.setRealName(realName);
        withdrawRequest.setStatus(OrderEnum.PENDDING.getCode());
        withdrawRequest.setWithdrawId(withdrawId);
        withdrawRequest.setCreateTime(DateUtils.getNowDate());
        int count = tWithdrawRequestMapper.insert(withdrawRequest);
        if (count>0) {
            customerService.changeBalance(userId, withdrawAmount, BillOperateTypeEnum.WITHDRAWAL, withdrawId, "提现");
        }
        return count>0;
    }

    @Override
    @Transactional
    public boolean approve(String withdrawId, Integer status,String remark) {
        TWithdrawRequest tWithdrawRequest = tWithdrawRequestMapper.selectTWithdrawRequestByWithdrawId(withdrawId);
        if (tWithdrawRequest == null) {
            throw new CustomException("参数错误");
        }
        Long userId = tWithdrawRequest.getCustomerId();
        UpdateWrapper<TWithdrawRequest> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("withdraw_id",withdrawId);
        updateWrapper.set("status",status);
        updateWrapper.set("remark",remark);
        boolean result = this.update(updateWrapper);
        if (result){
            if (status == 2) {//成功
                customerService.changeBalance(userId,tWithdrawRequest.getWithdrawAmount(), BillOperateTypeEnum.CONFIRM, withdrawId, "提现审核通过");
            }else if (status ==3){//拒绝
                customerService.changeBalance(userId,tWithdrawRequest.getWithdrawAmount(), BillOperateTypeEnum.REJECT, withdrawId, "提现审核拒绝");
            }
        }
        return result;
    }
}
