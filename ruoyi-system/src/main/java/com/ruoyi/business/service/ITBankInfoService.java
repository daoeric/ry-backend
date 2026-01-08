package com.ruoyi.business.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.business.domain.TBankInfo;

/**
 * 银行信息Service接口
 * 
 * @author ruoyi
 * @date 2026-01-05
 */
public interface ITBankInfoService extends IService<TBankInfo>
{
    /**
     * 查询银行信息
     * 
     * @param id 银行信息主键
     * @return 银行信息
     */
    public TBankInfo selectTBankInfoById(Long id);

    /**
     * 查询银行信息列表
     * 
     * @param tBankInfo 银行信息
     * @return 银行信息集合
     */
    public List<TBankInfo> selectTBankInfoList(TBankInfo tBankInfo);

    /**
     * 新增银行信息
     * 
     * @param tBankInfo 银行信息
     * @return 结果
     */
    public int insertTBankInfo(TBankInfo tBankInfo);

    /**
     * 修改银行信息
     * 
     * @param tBankInfo 银行信息
     * @return 结果
     */
    public int updateTBankInfo(TBankInfo tBankInfo);

    /**
     * 批量删除银行信息
     * 
     * @param ids 需要删除的银行信息主键集合
     * @return 结果
     */
    public int deleteTBankInfoByIds(Long[] ids);

    /**
     * 删除银行信息信息
     * 
     * @param id 银行信息主键
     * @return 结果
     */
    public int deleteTBankInfoById(Long id);

    Integer countByCustomerId(Long userId);
}
