package com.ruoyi.business.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.TVipMapper;
import com.ruoyi.business.domain.TVip;
import com.ruoyi.business.service.ITVipService;

/**
 * VIP管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-12
 */
@Service
public class TVipServiceImpl extends ServiceImpl<TVipMapper, TVip> implements ITVipService
{
    @Autowired
    private TVipMapper tVipMapper;

    /**
     * 查询VIP管理
     * 
     * @param id VIP管理主键
     * @return VIP管理
     */
    @Override
    public TVip selectTVipById(Integer id)
    {
        return tVipMapper.selectTVipById(id);
    }

    /**
     * 查询VIP管理列表
     * 
     * @param tVip VIP管理
     * @return VIP管理
     */
    @Override
    public List<TVip> selectTVipList(TVip tVip)
    {
        return tVipMapper.selectTVipList(tVip);
    }

    /**
     * 新增VIP管理
     * 
     * @param tVip VIP管理
     * @return 结果
     */
    @Override
    public int insertTVip(TVip tVip)
    {
        tVip.setCreateTime(DateUtils.getNowDate());
        return tVipMapper.insertTVip(tVip);
    }

    /**
     * 修改VIP管理
     * 
     * @param tVip VIP管理
     * @return 结果
     */
    @Override
    public int updateTVip(TVip tVip)
    {
        tVip.setUpdateTime(DateUtils.getNowDate());
        return tVipMapper.updateTVip(tVip);
    }

    /**
     * 批量删除VIP管理
     * 
     * @param ids 需要删除的VIP管理主键
     * @return 结果
     */
    @Override
    public int deleteTVipByIds(Integer[] ids)
    {
        return tVipMapper.deleteTVipByIds(ids);
    }

    /**
     * 删除VIP管理信息
     * 
     * @param id VIP管理主键
     * @return 结果
     */
    @Override
    public int deleteTVipById(Integer id)
    {
        return tVipMapper.deleteTVipById(id);
    }
}
