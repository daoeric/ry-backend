package com.ruoyi.business.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.business.domain.TVip;

/**
 * VIP管理Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
public interface TVipMapper extends BaseMapper<TVip>
{
    /**
     * 查询VIP管理
     * 
     * @param id VIP管理主键
     * @return VIP管理
     */
    public TVip selectTVipById(Integer id);

    /**
     * 查询VIP管理列表
     * 
     * @param tVip VIP管理
     * @return VIP管理集合
     */
    public List<TVip> selectTVipList(TVip tVip);

    /**
     * 新增VIP管理
     * 
     * @param tVip VIP管理
     * @return 结果
     */
    public int insertTVip(TVip tVip);

    /**
     * 修改VIP管理
     * 
     * @param tVip VIP管理
     * @return 结果
     */
    public int updateTVip(TVip tVip);

    /**
     * 删除VIP管理
     * 
     * @param id VIP管理主键
     * @return 结果
     */
    public int deleteTVipById(Integer id);

    /**
     * 批量删除VIP管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTVipByIds(Integer[] ids);
}
