package com.ruoyi.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.business.domain.TChannelRujin;

import java.math.BigDecimal;
import java.util.List;

/**
 * 入金渠道Service接口
 * 
 * @author nice
 * @date 2024-04-20
 */
public interface ITChannelRujinService extends IService<TChannelRujin>
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
     * 批量删除入金渠道
     * 
     * @param channelIds 需要删除的入金渠道ID
     * @return 结果
     */
    public int deleteTChannelRujinByIds(Integer[] channelIds);

    /**
     * 删除入金渠道信息
     * 
     * @param channelId 入金渠道ID
     * @return 结果
     */
    public int deleteTChannelRujinById(Integer channelId);

    /**
     * 获取入金提供商列表
     * @return
     */
    List<String> listAlias();

    List<String> listCodes();

    TChannelRujin getByChannelId(Integer channelId);

    TChannelRujin getChannel(String keyword, String productId);

    TChannelRujin getChannel(String keyword);


    boolean changeStatus(Integer channelId, Integer status);

    /**
     * 根据产品编号获取最大成本费率
     * @param productId
     * @return
     */
    BigDecimal getMaxRateByProductId(String productId);

    boolean checkExistChannel(String alias, String channelType,Integer channelId);
}
