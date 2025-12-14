package com.ruoyi.business.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.business.domain.TScanOrder;

/**
 * 扫描订单Service接口
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
public interface ITScanOrderService extends IService<TScanOrder>
{
    /**
     * 查询扫描订单
     * 
     * @param orderNo 扫描订单主键
     * @return 扫描订单
     */
    public TScanOrder selectTScanOrderByOrderNo(String orderNo);

    /**
     * 查询扫描订单列表
     * 
     * @param tScanOrder 扫描订单
     * @return 扫描订单集合
     */
    public List<TScanOrder> selectTScanOrderList(TScanOrder tScanOrder);

    /**
     * 新增扫描订单
     * 
     * @param tScanOrder 扫描订单
     * @return 结果
     */
    public int insertTScanOrder(TScanOrder tScanOrder);

    /**
     * 修改扫描订单
     * 
     * @param tScanOrder 扫描订单
     * @return 结果
     */
    public int updateTScanOrder(TScanOrder tScanOrder);

    /**
     * 批量删除扫描订单
     * 
     * @param orderNos 需要删除的扫描订单主键集合
     * @return 结果
     */
    public int deleteTScanOrderByOrderNos(String[] orderNos);

    /**
     * 删除扫描订单信息
     * 
     * @param orderNo 扫描订单主键
     * @return 结果
     */
    public int deleteTScanOrderByOrderNo(String orderNo);

    TScanOrder scan(Long userId, String barcode);


    TScanOrder selectTScanOrderByBarcode(String barcode);
}
