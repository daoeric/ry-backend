package com.ruoyi.business.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.TWhiteIpMapper;
import com.ruoyi.business.domain.TWhiteIp;
import com.ruoyi.business.service.ITWhiteIpService;

/**
 * 上游信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-01-04
 */
@Service
public class TWhiteIpServiceImpl extends ServiceImpl<TWhiteIpMapper,TWhiteIp> implements ITWhiteIpService
{
    @Autowired
    private TWhiteIpMapper tWhiteIpMapper;

    /**
     * 查询上游信息
     * 
     * @param id 上游信息主键
     * @return 上游信息
     */
    @Override
    public TWhiteIp selectTWhiteIpById(Integer id)
    {
        return tWhiteIpMapper.selectTWhiteIpById(id);
    }

    /**
     * 查询上游信息列表
     * 
     * @param tWhiteIp 上游信息
     * @return 上游信息
     */
    @Override
    public List<TWhiteIp> selectTWhiteIpList(TWhiteIp tWhiteIp)
    {
        return tWhiteIpMapper.selectTWhiteIpList(tWhiteIp);
    }

    /**
     * 新增上游信息
     * 
     * @param tWhiteIp 上游信息
     * @return 结果
     */
    @Override
    public int insertTWhiteIp(TWhiteIp tWhiteIp)
    {
        return tWhiteIpMapper.insertTWhiteIp(tWhiteIp);
    }

    /**
     * 修改上游信息
     * 
     * @param tWhiteIp 上游信息
     * @return 结果
     */
    @Override
    public int updateTWhiteIp(TWhiteIp tWhiteIp)
    {
        return tWhiteIpMapper.updateTWhiteIp(tWhiteIp);
    }

    /**
     * 批量删除上游信息
     * 
     * @param ids 需要删除的上游信息主键
     * @return 结果
     */
    @Override
    public int deleteTWhiteIpByIds(Integer[] ids)
    {
        return tWhiteIpMapper.deleteTWhiteIpByIds(ids);
    }

    /**
     * 删除上游信息信息
     * 
     * @param id 上游信息主键
     * @return 结果
     */
    @Override
    public int deleteTWhiteIpById(Integer id)
    {
        return tWhiteIpMapper.deleteTWhiteIpById(id);
    }

    @Override
    public boolean checkIp(String serviceKey, String requestIp) {
        boolean result = false;
        QueryWrapper<TWhiteIp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code",serviceKey);
        TWhiteIp whiteIp = this.getOne(queryWrapper);
        if (whiteIp!=null) {
            String ipAddress = whiteIp.getIpAddress();
            if (ipAddress.contains(requestIp)) {
                result = true;
            }
        } else { //如果没有配置默认 不需要校验白名单
            result = true;
        }
        return result;
    }
}
