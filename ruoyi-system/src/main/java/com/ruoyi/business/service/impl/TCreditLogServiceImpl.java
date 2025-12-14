package com.ruoyi.business.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.TCreditLogMapper;
import com.ruoyi.business.domain.TCreditLog;
import com.ruoyi.business.service.ITCreditLogService;

/**
 * 额度变更Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
@Service
public class TCreditLogServiceImpl extends ServiceImpl<TCreditLogMapper,TCreditLog> implements ITCreditLogService
{
    @Autowired
    private TCreditLogMapper tCreditLogMapper;

    /**
     * 查询额度变更
     * 
     * @param id 额度变更主键
     * @return 额度变更
     */
    @Override
    public TCreditLog selectTCreditLogById(Long id)
    {
        return tCreditLogMapper.selectTCreditLogById(id);
    }

    /**
     * 查询额度变更列表
     * 
     * @param tCreditLog 额度变更
     * @return 额度变更
     */
    @Override
    public List<TCreditLog> selectTCreditLogList(TCreditLog tCreditLog)
    {
        return tCreditLogMapper.selectTCreditLogList(tCreditLog);
    }

    /**
     * 新增额度变更
     * 
     * @param tCreditLog 额度变更
     * @return 结果
     */
    @Override
    public int insertTCreditLog(TCreditLog tCreditLog)
    {
        tCreditLog.setCreateTime(DateUtils.getNowDate());
        return tCreditLogMapper.insertTCreditLog(tCreditLog);
    }

    /**
     * 修改额度变更
     * 
     * @param tCreditLog 额度变更
     * @return 结果
     */
    @Override
    public int updateTCreditLog(TCreditLog tCreditLog)
    {
        tCreditLog.setUpdateTime(DateUtils.getNowDate());
        return tCreditLogMapper.updateTCreditLog(tCreditLog);
    }

    /**
     * 批量删除额度变更
     * 
     * @param ids 需要删除的额度变更主键
     * @return 结果
     */
    @Override
    public int deleteTCreditLogByIds(Long[] ids)
    {
        return tCreditLogMapper.deleteTCreditLogByIds(ids);
    }

    /**
     * 删除额度变更信息
     * 
     * @param id 额度变更主键
     * @return 结果
     */
    @Override
    public int deleteTCreditLogById(Long id)
    {
        return tCreditLogMapper.deleteTCreditLogById(id);
    }
}
