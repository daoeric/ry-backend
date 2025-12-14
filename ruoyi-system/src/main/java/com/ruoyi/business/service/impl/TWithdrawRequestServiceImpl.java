package com.ruoyi.business.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.TWithdrawRequestMapper;
import com.ruoyi.business.domain.TWithdrawRequest;
import com.ruoyi.business.service.ITWithdrawRequestService;

/**
 * 提现订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
@Service
public class TWithdrawRequestServiceImpl implements ITWithdrawRequestService 
{
    @Autowired
    private TWithdrawRequestMapper tWithdrawRequestMapper;

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
}
