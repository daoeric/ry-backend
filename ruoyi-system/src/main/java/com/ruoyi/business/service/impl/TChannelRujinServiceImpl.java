package com.ruoyi.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.business.domain.TChannelRujin;
import com.ruoyi.business.mapper.TChannelRujinMapper;
import com.ruoyi.business.service.ITChannelRujinService;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 入金渠道Service业务层处理
 * 
 * @author nice
 * @date 2024-04-20
 */
@Service
public class TChannelRujinServiceImpl extends ServiceImpl<TChannelRujinMapper, TChannelRujin> implements ITChannelRujinService
{
    @Autowired
    private TChannelRujinMapper tChannelRujinMapper;

    /**
     * 查询入金渠道
     * 
     * @param channelId 入金渠道ID
     * @return 入金渠道
     */
    @Override
    public TChannelRujin selectTChannelRujinById(Integer channelId)
    {
        TChannelRujin result = tChannelRujinMapper.selectTChannelRujinById(channelId);
        if (result != null && StringUtils.isNotEmpty(result.getProductId())) {
            result.setProductIds(result.getProductId().split(","));
        }
        return result;
    }

    /**
     * 查询入金渠道列表
     * 
     * @param tChannelRujin 入金渠道
     * @return 入金渠道
     */
    @Override
    public List<TChannelRujin> selectTChannelRujinList(TChannelRujin tChannelRujin)
    {
        return tChannelRujinMapper.selectTChannelRujinList(tChannelRujin);
    }

    /**
     * 新增入金渠道
     * 
     * @param tChannelRujin 入金渠道
     * @return 结果
     */
    @Override
    @Transactional
    public int insertTChannelRujin(TChannelRujin tChannelRujin)
    {
        tChannelRujin.setCreateTime(DateUtils.getNowDate());
        // 获取已对接过 其他属性
        QueryWrapper<TChannelRujin> wrapper = new QueryWrapper<>();
        wrapper.eq("alias",tChannelRujin.getAlias());
        List<TChannelRujin> list = this.list(wrapper);
        if (CollectionUtils.isEmpty(list)) {
            throw new CustomException("没有对接该上游，不允许新增！");
        }
        TChannelRujin channelRujin = list.get(0);
        channelRujin.setChannelId(null);
        channelRujin.setName(tChannelRujin.getName());
        channelRujin.setChannelRate(tChannelRujin.getChannelRate());
        channelRujin.setProductId(tChannelRujin.getProductId());
        channelRujin.setMinAmount(tChannelRujin.getMinAmount());
        channelRujin.setMaxAmount(tChannelRujin.getMaxAmount());
        channelRujin.setFixAmount(tChannelRujin.getFixAmount());
        channelRujin.setChannelType(tChannelRujin.getChannelType());
        int result = tChannelRujinMapper.insertTChannelRujin(channelRujin);
        return result;
    }

    /**
     * 修改入金渠道
     * 
     * @param channelRujin 入金渠道
     * @return 结果
     */
    @Override
    @Transactional
    public int updateTChannelRujin(TChannelRujin channelRujin)
    {
        channelRujin.setUpdateTime(DateUtils.getNowDate());
        // 删除之前产品和渠道的关联
        //userRoleMapper.deleteUserRoleByUserId(userId);
        return tChannelRujinMapper.updateTChannelRujin(channelRujin);
    }


    /**
     * 批量删除入金渠道
     * 
     * @param channelIds 需要删除的入金渠道ID
     * @return 结果
     */
    @Override
    public int deleteTChannelRujinByIds(Integer[] channelIds)
    {
        return tChannelRujinMapper.deleteTChannelRujinByIds(channelIds);
    }

    /**
     * 删除入金渠道信息
     * 
     * @param channelId 入金渠道ID
     * @return 结果
     */
    @Override
    public int deleteTChannelRujinById(Integer channelId)
    {
        TChannelRujin  channelRujin = this.getById(channelId);
        String alias = channelRujin.getAlias();

        // 如果上游只有最后一个不允许删除
        QueryWrapper<TChannelRujin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("alias",alias);

        return tChannelRujinMapper.deleteTChannelRujinById(channelId);
    }

    @Override
    public List<String> listAlias() {
        QueryWrapper<TChannelRujin> wrapper = new QueryWrapper<>();
        wrapper.select("alias");
        wrapper.groupBy("alias");
        List<Object> objectList = this.listObjs(wrapper);
        List<String>  result = Lists.newArrayList();
        for (Object o : objectList) {
            result.add(o.toString());
        }
        return result;
    }

    @Override
    public List<String> listCodes() {
        QueryWrapper<TChannelRujin> wrapper = new QueryWrapper<>();
        wrapper.select("code");
        wrapper.groupBy("code");
        List<Object> objectList = this.listObjs(wrapper);
        List<String>  result = Lists.newArrayList();
        for (Object o : objectList) {
            result.add(o.toString());
        }
        return result;
    }



    @Override
    public TChannelRujin getByChannelId(Integer channelId) {
        QueryWrapper<TChannelRujin> wrapper = new QueryWrapper<>();
        wrapper.eq("channel_id",channelId);
        return this.getOne(wrapper);
    }

    @Override
    public TChannelRujin getChannel(String keyword, String productId) {
        QueryWrapper<TChannelRujin>  wrapper = new QueryWrapper<>();
        wrapper.eq("code",keyword);
        wrapper.eq("product_id",productId);
        wrapper.last("limit 1");
        return this.getOne(wrapper);
    }

    @Override
    public TChannelRujin getChannel(String keyword) {
        QueryWrapper<TChannelRujin>  wrapper = new QueryWrapper<>();
        wrapper.eq("code",keyword);
        wrapper.last("limit 1");
        return this.getOne(wrapper);
    }


    @Override
    public boolean changeStatus(Integer channelId, Integer status) {
        UpdateWrapper<TChannelRujin> wrapper = new UpdateWrapper<>();
        wrapper.eq("channel_id",channelId);
        wrapper.set("status",status);
        return this.update(wrapper);
    }

    @Override
    public BigDecimal getMaxRateByProductId(String productId) {
        return tChannelRujinMapper.getMaxRateByProductId(productId);
    }

    @Override
    public boolean checkExistChannel(String alias, String channelType,Integer channelId) {
        QueryWrapper<TChannelRujin>  wrapper = new QueryWrapper<>();
        wrapper.eq("alias",alias);
        wrapper.eq("channel_type",channelType);
        if (channelId != null) {
            wrapper.ne("channel_id",channelId);
        }
        return this.count(wrapper)>0;
    }

}
