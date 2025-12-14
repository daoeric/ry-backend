package com.ruoyi.business.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.TPaymentRequestMapper;
import com.ruoyi.business.domain.TPaymentRequest;
import com.ruoyi.business.service.ITPaymentRequestService;

/**
 * 存入订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
@Service
public class TPaymentRequestServiceImpl implements ITPaymentRequestService 
{
    @Autowired
    private TPaymentRequestMapper tPaymentRequestMapper;

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
}
