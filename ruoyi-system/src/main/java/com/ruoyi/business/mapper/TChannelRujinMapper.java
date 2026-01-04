package com.ruoyi.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.business.domain.TChannelRujin;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

/**
 * 入金渠道Mapper接口
 * 
 * @author nice
 * @date 2024-04-20
 */
public interface TChannelRujinMapper extends BaseMapper<TChannelRujin>
{
    /**
     * 查询入金渠道
     * 
     * @param channelId 入金渠道ID
     * @return 入金渠道
     */
    public TChannelRujin selectTChannelRujinById(Integer channelId);

    /**
     * 查询入金渠道列表
     * 
     * @param tChannelRujin 入金渠道
     * @return 入金渠道集合
     */
    public List<TChannelRujin> selectTChannelRujinList(TChannelRujin tChannelRujin);

    /**
     * 新增入金渠道
     *
     * @param tChannelRujin 入金渠道
     * @return 结果
     */
    public int insertTChannelRujin(TChannelRujin tChannelRujin);

    /**
     * 修改入金渠道
     * 
     * @param tChannelRujin 入金渠道
     * @return 结果
     */
    public int updateTChannelRujin(TChannelRujin tChannelRujin);

    /**
     * 删除入金渠道
     * 
     * @param channelId 入金渠道ID
     * @return 结果
     */
    public int deleteTChannelRujinById(Integer channelId);

    /**
     * 批量删除入金渠道
     * 
     * @param channelIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTChannelRujinByIds(Integer[] channelIds);


    /**
     * 获取绑定了 productId 的通道
     * @param productId
     * @return
     */
    List<TChannelRujin> listByProductId(String productId);

    @Select("SELECT min(channel_rate) FROM t_channel_rujin WHERE FIND_IN_SET(#{productId}, product_id)")
    BigDecimal getMaxRateByProductId(String productId);
}
