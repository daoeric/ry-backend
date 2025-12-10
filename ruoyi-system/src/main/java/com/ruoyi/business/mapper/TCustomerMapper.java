package com.ruoyi.business.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.business.domain.TCustomer;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 用户管理Mapper接口
 * 
 * @author ruoyi
 * @date 2025-12-08
 */
public interface TCustomerMapper extends BaseMapper<TCustomer>
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
     * 删除用户管理
     * 
     * @param id 用户管理主键
     * @return 结果
     */
    public int deleteTCustomerById(Long id);

    /**
     * 批量删除用户管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTCustomerByIds(Long[] ids);


    @Select("select * from t_customer where username = #{username}")
    TCustomer selectTCustomerByUsername(@Param("username") String username);

    @Select("select * from t_customer where invite_code = #{inviteCode} limit 1")
    TCustomer selectOneByInviteCode(@Param("inviteCode") String inviteCode);
}
