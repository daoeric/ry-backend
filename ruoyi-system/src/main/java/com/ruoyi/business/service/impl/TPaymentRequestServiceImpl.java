package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.TChannelRujin;
import com.ruoyi.business.domain.TCustomer;
import com.ruoyi.business.domain.TPaymentRequest;
import com.ruoyi.business.domain.TVip;
import com.ruoyi.business.mapper.TPaymentRequestMapper;
import com.ruoyi.business.service.ITChannelRujinService;
import com.ruoyi.business.service.ITCustomerService;
import com.ruoyi.business.service.ITPaymentRequestService;
import com.ruoyi.business.service.ITVipService;
import com.ruoyi.common.dto.payment.DepositDto;
import com.ruoyi.common.enums.BillOperateTypeEnum;
import com.ruoyi.common.enums.OrderEnum;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.payment.DepositResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.common.utils.uuid.SnowflakeKeyGenerator;
import com.ruoyi.payment.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 存入订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
@Service
@Slf4j
public class TPaymentRequestServiceImpl implements ITPaymentRequestService 
{
    @Autowired
    private TPaymentRequestMapper tPaymentRequestMapper;


    @Autowired
    private SnowflakeKeyGenerator snowflakeKeyGenerator;

    @Autowired
    private ITCustomerService customerService;

    @Autowired
    private ITChannelRujinService channelRujinServicel;

    @Autowired
    private ITVipService vipService;



    /**
     * 查询存入订单
     * 
     * @param requestId 存入订单主键
     * @return 存入订单
     */
    @Override
    public TPaymentRequest selectTPaymentRequestByRequestId(String requestId)
    {
        return tPaymentRequestMapper.selectTPaymentRequestByRequestId(requestId);
    }

    /**
     * 查询存入订单列表
     * 
     * @param tPaymentRequest 存入订单
     * @return 存入订单
     */
    @Override
    public List<TPaymentRequest> selectTPaymentRequestList(TPaymentRequest tPaymentRequest)
    {
        return tPaymentRequestMapper.selectTPaymentRequestList(tPaymentRequest);
    }

    /**
     * 新增存入订单
     * 
     * @param tPaymentRequest 存入订单
     * @return 结果
     */
    @Override
    public int insertTPaymentRequest(TPaymentRequest tPaymentRequest)
    {
        tPaymentRequest.setCreateTime(DateUtils.getNowDate());
        return tPaymentRequestMapper.insertTPaymentRequest(tPaymentRequest);
    }

    /**
     * 修改存入订单
     * 
     * @param tPaymentRequest 存入订单
     * @return 结果
     */
    @Override
    public int updateTPaymentRequest(TPaymentRequest tPaymentRequest)
    {
        tPaymentRequest.setUpdateTime(DateUtils.getNowDate());
        return tPaymentRequestMapper.updateTPaymentRequest(tPaymentRequest);
    }

    /**
     * 批量删除存入订单
     * 
     * @param requestIds 需要删除的存入订单主键
     * @return 结果
     */
    @Override
    public int deleteTPaymentRequestByRequestIds(String[] requestIds)
    {
        return tPaymentRequestMapper.deleteTPaymentRequestByRequestIds(requestIds);
    }

    /**
     * 删除存入订单信息
     * 
     * @param requestId 存入订单主键
     * @return 结果
     */
    @Override
    public int deleteTPaymentRequestByRequestId(String requestId)
    {
        return tPaymentRequestMapper.deleteTPaymentRequestByRequestId(requestId);
    }

    @Override
    public DepositResult deposit(Long userId, String username, BigDecimal orderAmount) {
        DepositResult result = new DepositResult();
        String requestId = "DP"+snowflakeKeyGenerator.generateKey();
        TPaymentRequest paymentRequest = new TPaymentRequest();
        paymentRequest.setRequestId(requestId);

        paymentRequest.setCustomerId(userId);
        paymentRequest.setUsername(username);
        paymentRequest.setOrderAmount(orderAmount);
        Date now = new Date();
        paymentRequest.setCreateTime(now);
        paymentRequest.setUpdateTime(now);
        paymentRequest.setStatus(OrderEnum.PENDDING.getCode());
        //TODO 三方订单创建
        IPaymentService paymentService = SpringUtils.getBean("lppay");
        TChannelRujin channelRujin = channelRujinServicel.getChannel("lppay");
        DepositDto depositDto = new DepositDto();
        depositDto.setUserId(userId);
        depositDto.setPay_amount(orderAmount);
        DepositResult depositResult = paymentService.deposit(depositDto,requestId,channelRujin);
        if (depositResult.getCode() == 200) {
            paymentRequest.setStatus(OrderEnum.PENDDING.getCode());
        } else {
            log.info("创建订单失败：{}",depositResult);
            paymentRequest.setStatus(OrderEnum.ERROR.getCode());
        }
        tPaymentRequestMapper.insertTPaymentRequest(paymentRequest);
        return depositResult;
    }

    @Override
    @Transactional
    public DepositResult pay(DepositDto depositDto) {
        IPaymentService paymentService = SpringUtils.getBean("huifeng");
        TChannelRujin channelRujin = channelRujinServicel.getChannel("huifeng");
        String requestId = "DP"+snowflakeKeyGenerator.generateKey();
        DepositResult depositResult = paymentService.deposit(depositDto,requestId,channelRujin);

        return depositResult;
    }

    @Override
    @Transactional
    public boolean doSuccess(String billNo, BigDecimal amount,String remark) {
        //设置订单成已支付
        boolean result = true;
        Date now = DateUtils.getNowDate();
        TPaymentRequest paymentRequest = this.selectTPaymentRequestByRequestId(billNo);
        if (paymentRequest == null || (!OrderEnum.PENDDING.getCode().equals(paymentRequest.getStatus()))) {
            throw new CustomException("订单："+ billNo +"已经被处理过了！");
        }

        BigDecimal orderAmount = paymentRequest.getOrderAmount();
        BigDecimal realAmount = amount == null ? orderAmount:amount;
        Long userId = paymentRequest.getCustomerId();
        TCustomer customer = customerService.getById(userId);
        paymentRequest.setSuccessTime(now);
        paymentRequest.setUpdateBy(customer.getUsername());
        paymentRequest.setUpdateTime(now);
        paymentRequest.setRealAmount(realAmount);
        paymentRequest.setStatus(OrderEnum.SUCCESS.getCode());
        paymentRequest.setRemark(remark);
        int count = this.updateTPaymentRequest(paymentRequest);
        if(count>0){
            //更新用户额度 API入金->商户订单号
            result = customerService.changeBalance(userId,realAmount,BillOperateTypeEnum.DEPOSIT,billNo,paymentRequest.getRequestId());
            // 充值成功后，查看VIP是否达到升级条件，如果达到了，自动升级
            // orderAmount 和VIP等级比较，如果满足条件，则升级
            List<TVip> vipList = vipService.selectUpdateVip();
            for (TVip vip : vipList) {
                if (orderAmount.compareTo(vip.getDepositCondition()) >= 0 && customer.getGrade()<vip.getId()) {
                    customerService.changeVipLevel(userId,vip.getId());
                    break;
                }
            }
        }
        return  result;
    }
}
