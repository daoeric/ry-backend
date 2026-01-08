package com.ruoyi.business.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.business.domain.TBankInfo;

/**
 * 银行信息Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-05
 */
public interface TBankInfoMapper extends BaseMapper<TBankInfo>
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
     * 删除银行信息
     * 
     * @param id 银行信息主键
     * @return 结果
     */
    public int deleteTBankInfoById(Long id);

    /**
     * 批量删除银行信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTBankInfoByIds(Long[] ids);
}
