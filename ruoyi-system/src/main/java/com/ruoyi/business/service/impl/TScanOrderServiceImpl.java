package com.ruoyi.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.business.domain.TCustomer;
import com.ruoyi.business.domain.TScanOrder;
import com.ruoyi.business.domain.TVip;
import com.ruoyi.business.mapper.TScanOrderMapper;
import com.ruoyi.business.service.ITCustomerService;
import com.ruoyi.business.service.ITScanOrderService;
import com.ruoyi.business.service.ITVipService;
import com.ruoyi.common.enums.BillOperateTypeEnum;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.uuid.SnowflakeKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 扫描订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
@Service
public class TScanOrderServiceImpl extends ServiceImpl<TScanOrderMapper, TScanOrder> implements ITScanOrderService
{
    @Autowired
    private TScanOrderMapper tScanOrderMapper;

    @Autowired
    private ITVipService vipService;

    @Autowired
    private ITCustomerService customerService;

    @Autowired
    private SnowflakeKeyGenerator snowflakeKeyGenerator;

    /**
     * 查询扫描订单
     * 
     * @param orderNo 扫描订单主键
     * @return 扫描订单
     */
    @Override
    public TScanOrder selectTScanOrderByOrderNo(String orderNo)
    {
        return tScanOrderMapper.selectTScanOrderByOrderNo(orderNo);
    }

    /**
     * 查询扫描订单列表
     * 
     * @param tScanOrder 扫描订单
     * @return 扫描订单
     */
    @Override
    public List<TScanOrder> selectTScanOrderList(TScanOrder tScanOrder)
    {
        return tScanOrderMapper.selectTScanOrderList(tScanOrder);
    }

    /**
     * 新增扫描订单
     * 
     * @param tScanOrder 扫描订单
     * @return 结果
     */
    @Override
    public int insertTScanOrder(TScanOrder tScanOrder)
    {
        tScanOrder.setCreateTime(DateUtils.getNowDate());
        return tScanOrderMapper.insertTScanOrder(tScanOrder);
    }

    /**
     * 修改扫描订单
     * 
     * @param tScanOrder 扫描订单
     * @return 结果
     */
    @Override
    public int updateTScanOrder(TScanOrder tScanOrder)
    {
        return tScanOrderMapper.updateTScanOrder(tScanOrder);
    }

    /**
     * 批量删除扫描订单
     * 
     * @param orderNos 需要删除的扫描订单主键
     * @return 结果
     */
    @Override
    public int deleteTScanOrderByOrderNos(String[] orderNos)
    {
        return tScanOrderMapper.deleteTScanOrderByOrderNos(orderNos);
    }

    /**
     * 删除扫描订单信息
     * 
     * @param orderNo 扫描订单主键
     * @return 结果
     */
    @Override
    public int deleteTScanOrderByOrderNo(String orderNo)
    {
        return tScanOrderMapper.deleteTScanOrderByOrderNo(orderNo);
    }

    @Override
    @Transactional
    public TScanOrder scan(Long userId, String barcode) {

        TCustomer customer = customerService.getById(userId);
        Integer grade = customer.getGrade();
        TVip vip = vipService.getById(grade);
        BigDecimal minReward = vip.getMinReward();
        BigDecimal maxReward = vip.getMaxReward();
        //根据最小奖励金额和最大奖励金额之间随机一个金额
        BigDecimal rewardAmount = minReward.add(maxReward.subtract(minReward).multiply(new BigDecimal(Math.random())));
        //构建TScanOrder对象
        TScanOrder tScanOrder = new TScanOrder();
        String orderNo = "SCAN" + snowflakeKeyGenerator.generateKey();
        tScanOrder.setOrderNo(barcode);
        tScanOrder.setCustomerId(userId);
        tScanOrder.setUsername(customer.getUsername());
        tScanOrder.setBarcode(barcode);
        tScanOrder.setCreateTime(DateUtils.getNowDate());
        tScanOrder.setStatus(2);
        tScanOrder.setRewardAmount(rewardAmount);
        boolean flag = this.save(tScanOrder);
        if (flag) {
            //给用户增加奖励
            customerService.changeBalance(userId,rewardAmount, BillOperateTypeEnum.COMMISSION,orderNo, "扫码奖励");

        }
        return flag?tScanOrder:null;
    }

    @Override
    public TScanOrder selectTScanOrderByBarcode(String barcode) {
        QueryWrapper<TScanOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("barcode",barcode);
        return this.getOne(wrapper);
    }

}
