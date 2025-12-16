package com.ruoyi.business.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.business.domain.TCreditLog;
import com.ruoyi.business.service.ITCreditLogService;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.enums.BillOperateTypeEnum;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.RedisLock;
import com.ruoyi.common.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.TCustomerMapper;
import com.ruoyi.business.domain.TCustomer;
import com.ruoyi.business.service.ITCustomerService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-08
 */
@Service
@Slf4j
public class TCustomerServiceImpl  extends ServiceImpl<TCustomerMapper, TCustomer> implements ITCustomerService
{
    @Autowired
    private TCustomerMapper tCustomerMapper;

    @Autowired
    private RedisLock redisLock;

    @Autowired
    private ITCreditLogService creditLogService;

    /**
     * 查询用户管理
     * 
     * @param id 用户管理主键
     * @return 用户管理
     */
    @Override
    public TCustomer selectTCustomerById(Long id)
    {
        return tCustomerMapper.selectTCustomerById(id);
    }

    /**
     * 查询用户管理列表
     * 
     * @param tCustomer 用户管理
     * @return 用户管理
     */
    @Override
    public List<TCustomer> selectTCustomerList(TCustomer tCustomer)
    {
        return tCustomerMapper.selectTCustomerList(tCustomer);
    }

    /**
     * 新增用户管理
     * 
     * @param tCustomer 用户管理
     * @return 结果
     */
    @Override
    public int insertTCustomer(TCustomer tCustomer)
    {
        tCustomer.setCreateTime(DateUtils.getNowDate());
        return tCustomerMapper.insertTCustomer(tCustomer);
    }

    /**
     * 修改用户管理
     * 
     * @param tCustomer 用户管理
     * @return 结果
     */
    @Override
    public int updateTCustomer(TCustomer tCustomer)
    {
        tCustomer.setUpdateTime(DateUtils.getNowDate());
        return tCustomerMapper.updateTCustomer(tCustomer);
    }

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的用户管理主键
     * @return 结果
     */
    @Override
    public int deleteTCustomerByIds(Long[] ids)
    {
        return tCustomerMapper.deleteTCustomerByIds(ids);
    }

    /**
     * 删除用户管理信息
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    @Override
    public int deleteTCustomerById(Long id)
    {
        return tCustomerMapper.deleteTCustomerById(id);
    }

    @Override
    public TCustomer selectTCustomerByUsername(String username) {

        return tCustomerMapper.selectTCustomerByUsername(username);

    }

    @Override
    public TCustomer selectTCustomerByInviteCode(String inviteCode) {
        TCustomer tCustomer = tCustomerMapper.selectOneByInviteCode(inviteCode);
        return tCustomer;
    }

    @Override
    @Transactional
    public boolean changeBalance(Long userId, BigDecimal number, BillOperateTypeEnum type, String refId, String remark) {
        String key = "changeBalance:"+userId;
        try{
            if(redisLock.tryLock(key, 3, 10, TimeUnit.SECONDS)){
                UpdateWrapper<TCustomer> updateWrapper = new UpdateWrapper<>();
                TCustomer customer = this.getById(userId);
                BigDecimal prebalance = customer.getBalance();
                BigDecimal postBalance;
                BigDecimal showAmount = number;
                int opearteType = type.getCode();;
                if (BillOperateTypeEnum.MANUAL_IN.equals(type)) {//手动调整余额
                    postBalance = prebalance.add(number);
                    updateWrapper.setSql("balance = balance + " + number);
                } else if(BillOperateTypeEnum.DEPOSIT.equals(type)) {//存款
                    updateWrapper.setSql("balance = balance + " + number);
                    postBalance = prebalance.add(number);
                } else if (BillOperateTypeEnum.WITHDRAWAL.equals(type)) { //提款
                    updateWrapper.setSql("balance = balance - " + number + ",lock_balance = lock_balance + "+ number);
                    showAmount = number.negate();
                    postBalance = prebalance.subtract(number);
                } else if(BillOperateTypeEnum.REJECT.equals(type)){ //提款驳回
                    updateWrapper.setSql("balance = balance + " + number + ",lock_balance = lock_balance - "+ number);
                    postBalance = prebalance.add(number);
                } else if(BillOperateTypeEnum.COMMISSION.equals(type)){ //添加佣金
                    updateWrapper.setSql("balance = balance + " + number );
                    postBalance = prebalance.add(number);
                }else if(BillOperateTypeEnum.CONFIRM.equals(type)){ //代付通过
                    updateWrapper.setSql("lock_balance = lock_balance - " + number );
                    postBalance = prebalance;
                }else {
                    return false;
                }
                if (postBalance.compareTo(BigDecimal.ZERO)<0) {
                    throw new CustomException("余额不能调整为负数");
                }
                updateWrapper.eq("id",userId);
                boolean result = this.update(updateWrapper);
                if (result && prebalance.compareTo(postBalance) !=0) {
                    TCreditLog creditDsLog = new TCreditLog();
                    creditDsLog.setCustomerId(userId);
                    creditDsLog.setRemark(remark);
                    creditDsLog.setPreBalance(prebalance);
                    creditDsLog.setPostBalance(postBalance);
                    creditDsLog.setOpearteAmount(showAmount);
                    creditDsLog.setOpearteType(opearteType);
                    creditDsLog.setCreateTime(new Date());
                    creditDsLog.setCreateBy(Constants.SYSTEM);
                    creditDsLog.setRefId(refId);
                    creditLogService.save(creditDsLog);
                }
                return result;
            } else {
                log.info("{}修改代收余额时没有竞争到锁！订单号：{},修改金额:{}",userId,refId,number);
                throw new CustomException("系统正忙，请稍后再试！");
            }

        } catch (Exception e){
            throw e;
        } finally {
            redisLock.unlock(key);
        }
    }
    
    @Override
    public boolean updatePwd(Long id, String newPassword) {
        UpdateWrapper<TCustomer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        updateWrapper.set("password", SecurityUtils.encryptPassword(newPassword));
        return this.update(updateWrapper);
    }
    
    @Override
    public boolean updateRealnameStatus(Long id, Integer realnameStatus) {
        UpdateWrapper<TCustomer> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("realname_status", realnameStatus);
        return this.update(updateWrapper);
    }
}