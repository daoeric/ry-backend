package com.ruoyi.business.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.business.domain.TWhiteIp;

/**
 * 上游信息Mapper接口
 * 
 * @author ruoyi
 * @date 2026-01-04
 */
public interface TWhiteIpMapper extends BaseMapper<TWhiteIp>
{
    /**
     * 查询上游信息
     * 
     * @param id 上游信息主键
     * @return 上游信息
     */
    public TWhiteIp selectTWhiteIpById(Integer id);

    /**
     * 查询上游信息列表
     * 
     * @param tWhiteIp 上游信息
     * @return 上游信息集合
     */
    public List<TWhiteIp> selectTWhiteIpList(TWhiteIp tWhiteIp);

    /**
     * 新增上游信息
     * 
     * @param tWhiteIp 上游信息
     * @return 结果
     */
    public int insertTWhiteIp(TWhiteIp tWhiteIp);

    /**
     * 修改上游信息
     * 
     * @param tWhiteIp 上游信息
     * @return 结果
     */
    public int updateTWhiteIp(TWhiteIp tWhiteIp);

    /**
     * 删除上游信息
     * 
     * @param id 上游信息主键
     * @return 结果
     */
    public int deleteTWhiteIpById(Integer id);

    /**
     * 批量删除上游信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTWhiteIpByIds(Integer[] ids);
}
