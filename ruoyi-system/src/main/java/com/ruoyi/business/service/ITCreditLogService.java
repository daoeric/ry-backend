package com.ruoyi.business.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.business.domain.TCreditLog;

/**
 * 额度变更Service接口
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
public interface ITCreditLogService extends IService<TCreditLog>
{
    /**
     * 查询额度变更
     * 
     * @param id 额度变更主键
     * @return 额度变更
     */
    public TCreditLog selectTCreditLogById(Long id);

    /**
     * 查询额度变更列表
     * 
     * @param tCreditLog 额度变更
     * @return 额度变更集合
     */
    public List<TCreditLog> selectTCreditLogList(TCreditLog tCreditLog);

    /**
     * 新增额度变更
     * 
     * @param tCreditLog 额度变更
     * @return 结果
     */
    public int insertTCreditLog(TCreditLog tCreditLog);

    /**
     * 修改额度变更
     * 
     * @param tCreditLog 额度变更
     * @return 结果
     */
    public int updateTCreditLog(TCreditLog tCreditLog);

    /**
     * 批量删除额度变更
     * 
     * @param ids 需要删除的额度变更主键集合
     * @return 结果
     */
    public int deleteTCreditLogByIds(Long[] ids);

    /**
     * 删除额度变更信息
     * 
     * @param id 额度变更主键
     * @return 结果
     */
    public int deleteTCreditLogById(Long id);
}
