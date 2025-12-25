package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.common.core.domain.model.LoginMerchantUser;
import com.ruoyi.system.domain.SysUserOnline;

/**
 * 商户在线用户 服务层
 * 
 * @author ruoyi
 */
public interface IMerchantOnlineService
{
    /**
     * 通过登录地址查询商户信息
     * 
     * @param ipaddr 登录地址
     * @param user 用户信息
     * @return 在线商户用户信息
     */
    public SysUserOnline selectMerchantOnlineByIpaddr(String ipaddr, LoginMerchantUser user);

    /**
     * 通过用户名称查询商户信息
     * 
     * @param userName 用户名称
     * @param user 用户信息
     * @return 在线商户用户信息
     */
    public SysUserOnline selectMerchantOnlineByUserName(String userName, LoginMerchantUser user);

    /**
     * 通过登录地址/用户名称查询商户信息
     * 
     * @param ipaddr 登录地址
     * @param userName 用户名称
     * @param user 用户信息
     * @return 在线商户用户信息
     */
    public SysUserOnline selectMerchantOnlineByInfo(String ipaddr, String userName, LoginMerchantUser user);

    /**
     * 设置在线商户用户信息
     * 
     * @param user 用户信息
     * @return 在线商户用户
     */
    public SysUserOnline loginUserToMerchantUserOnline(LoginMerchantUser user);
}