package com.ruoyi.business.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.TCustomerMapper;
import com.ruoyi.business.domain.TCustomer;
import com.ruoyi.business.service.ITCustomerService;

/**
 * 用户管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-12-08
 */
@Service
public class TCustomerServiceImpl  extends ServiceImpl<TCustomerMapper, TCustomer> implements ITCustomerService
{
    @Autowired
    private TCustomerMapper tCustomerMapper;

    /**
     * 查询用户管理
     * 
     * @param id 用户管理主键
     * @return 用户管理
     */
    @Override
    public TCustomer selectTCustomerById(Long id)
    {
        return tCustomerMapper.selectTCustomerById(id);
    }

    /**
     * 查询用户管理列表
     * 
     * @param tCustomer 用户管理
     * @return 用户管理
     */
    @Override
    public List<TCustomer> selectTCustomerList(TCustomer tCustomer)
    {
        return tCustomerMapper.selectTCustomerList(tCustomer);
    }

    /**
     * 新增用户管理
     * 
     * @param tCustomer 用户管理
     * @return 结果
     */
    @Override
    public int insertTCustomer(TCustomer tCustomer)
    {
        tCustomer.setCreateTime(DateUtils.getNowDate());
        return tCustomerMapper.insertTCustomer(tCustomer);
    }

    /**
     * 修改用户管理
     * 
     * @param tCustomer 用户管理
     * @return 结果
     */
    @Override
    public int updateTCustomer(TCustomer tCustomer)
    {
        tCustomer.setUpdateTime(DateUtils.getNowDate());
        return tCustomerMapper.updateTCustomer(tCustomer);
    }

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的用户管理主键
     * @return 结果
     */
    @Override
    public int deleteTCustomerByIds(Long[] ids)
    {
        return tCustomerMapper.deleteTCustomerByIds(ids);
    }

    /**
     * 删除用户管理信息
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    @Override
    public int deleteTCustomerById(Long id)
    {
        return tCustomerMapper.deleteTCustomerById(id);
    }

    @Override
    public TCustomer selectTCustomerByUsername(String username) {

        return tCustomerMapper.selectTCustomerByUsername(username);

    }

    @Override
    public TCustomer selectTCustomerByInviteCode(String inviteCode) {
        TCustomer tCustomer = tCustomerMapper.selectOneByInviteCode(inviteCode);
        return tCustomer;
    }
}
