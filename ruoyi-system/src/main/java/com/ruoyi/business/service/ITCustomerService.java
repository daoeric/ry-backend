package com.ruoyi.business.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.business.domain.TCustomer;

/**
 * 用户管理Service接口
 * 
 * @author ruoyi
 * @date 2025-12-08
 */
public interface ITCustomerService extends IService<TCustomer>
{
    /**
     * 查询用户管理
     * 
     * @param id 用户管理主键
     * @return 用户管理
     */
    public TCustomer selectTCustomerById(Long id);

    /**
     * 查询用户管理列表
     * 
     * @param tCustomer 用户管理
     * @return 用户管理集合
     */
    public List<TCustomer> selectTCustomerList(TCustomer tCustomer);

    /**
     * 新增用户管理
     * 
     * @param tCustomer 用户管理
     * @return 结果
     */
    public int insertTCustomer(TCustomer tCustomer);

    /**
     * 修改用户管理
     * 
     * @param tCustomer 用户管理
     * @return 结果
     */
    public int updateTCustomer(TCustomer tCustomer);

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的用户管理主键集合
     * @return 结果
     */
    public int deleteTCustomerByIds(Long[] ids);

    /**
     * 删除用户管理信息
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    public int deleteTCustomerById(Long id);

    TCustomer selectTCustomerByUsername(String username);
}
