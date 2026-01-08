package com.ruoyi.business.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.business.domain.TWithdrawRequest;

/**
 * 提现订单Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
public interface TWithdrawRequestMapper extends BaseMapper<TWithdrawRequest>
{
    /**
     * 查询提现订单
     * 
     * @param withdrawId 提现订单主键
     * @return 提现订单
     */
    public TWithdrawRequest selectTWithdrawRequestByWithdrawId(String withdrawId);

    /**
     * 查询提现订单列表
     * 
     * @param tWithdrawRequest 提现订单
     * @return 提现订单集合
     */
    public List<TWithdrawRequest> selectTWithdrawRequestList(TWithdrawRequest tWithdrawRequest);

    /**
     * 新增提现订单
     * 
     * @param tWithdrawRequest 提现订单
     * @return 结果
     */
    public int insertTWithdrawRequest(TWithdrawRequest tWithdrawRequest);

    /**
     * 修改提现订单
     * 
     * @param tWithdrawRequest 提现订单
     * @return 结果
     */
    public int updateTWithdrawRequest(TWithdrawRequest tWithdrawRequest);

    /**
     * 删除提现订单
     * 
     * @param withdrawId 提现订单主键
     * @return 结果
     */
    public int deleteTWithdrawRequestByWithdrawId(String withdrawId);

    /**
     * 批量删除提现订单
     * 
     * @param withdrawIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTWithdrawRequestByWithdrawIds(String[] withdrawIds);
}
