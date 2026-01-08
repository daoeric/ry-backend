package com.ruoyi.business.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.TBankInfoMapper;
import com.ruoyi.business.domain.TBankInfo;
import com.ruoyi.business.service.ITBankInfoService;

/**
 * 银行信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-05
 */
@Service
public class TBankInfoServiceImpl extends ServiceImpl<TBankInfoMapper,TBankInfo> implements ITBankInfoService
{
    @Autowired
    private TBankInfoMapper tBankInfoMapper;

    /**
     * 查询银行信息
     * 
     * @param id 银行信息主键
     * @return 银行信息
     */
    @Override
    public TBankInfo selectTBankInfoById(Long id)
    {
        return tBankInfoMapper.selectTBankInfoById(id);
    }

    /**
     * 查询银行信息列表
     * 
     * @param tBankInfo 银行信息
     * @return 银行信息
     */
    @Override
    public List<TBankInfo> selectTBankInfoList(TBankInfo tBankInfo)
    {
        return tBankInfoMapper.selectTBankInfoList(tBankInfo);
    }

    /**
     * 新增银行信息
     * 
     * @param tBankInfo 银行信息
     * @return 结果
     */
    @Override
    public int insertTBankInfo(TBankInfo tBankInfo)
    {
        tBankInfo.setCreateTime(DateUtils.getNowDate());
        return tBankInfoMapper.insertTBankInfo(tBankInfo);
    }

    /**
     * 修改银行信息
     * 
     * @param tBankInfo 银行信息
     * @return 结果
     */
    @Override
    public int updateTBankInfo(TBankInfo tBankInfo)
    {
        tBankInfo.setUpdateTime(DateUtils.getNowDate());
        return tBankInfoMapper.updateTBankInfo(tBankInfo);
    }

    /**
     * 批量删除银行信息
     * 
     * @param ids 需要删除的银行信息主键
     * @return 结果
     */
    @Override
    public int deleteTBankInfoByIds(Long[] ids)
    {
        return tBankInfoMapper.deleteTBankInfoByIds(ids);
    }

    /**
     * 删除银行信息信息
     * 
     * @param id 银行信息主键
     * @return 结果
     */
    @Override
    public int deleteTBankInfoById(Long id)
    {
        return tBankInfoMapper.deleteTBankInfoById(id);
    }

    @Override
    public Integer countByCustomerId(Long userId) {
        QueryWrapper<TBankInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("customer_id",userId);
        return Math.toIntExact(tBankInfoMapper.selectCount(wrapper));
    }
}
