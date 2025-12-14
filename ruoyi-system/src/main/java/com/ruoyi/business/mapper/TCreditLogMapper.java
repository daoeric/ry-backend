package com.ruoyi.business.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.business.domain.TCreditLog;

/**
 * 额度变更Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
public interface TCreditLogMapper extends BaseMapper<TCreditLog>
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
     * 删除额度变更
     * 
     * @param id 额度变更主键
     * @return 结果
     */
    public int deleteTCreditLogById(Long id);

    /**
     * 批量删除额度变更
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTCreditLogByIds(Long[] ids);
}
