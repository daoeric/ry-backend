package com.ruoyi.framework.web.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.domain.model.LoginMerchantUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysUserOnline;
import com.ruoyi.system.service.IMerchantOnlineService;

/**
 * 商户在线用户服务
 * 
 * @author ruoyi
 */
@Service
public class MerchantOnlineService implements IMerchantOnlineService
{
    @Autowired
    private RedisCache redisCache;

    /**
     * 获取商户在线用户列表
     * 
     * @return 在线商户用户列表
     */
    @Override
    public SysUserOnline selectMerchantOnlineByIpaddr(String ipaddr, LoginMerchantUser user)
    {
        if (user != null && StringUtils.equals(ipaddr, user.getIpaddr()))
        {
            return loginUserToMerchantUserOnline(user);
        }
        return null;
    }

    @Override
    public SysUserOnline selectMerchantOnlineByUserName(String userName, LoginMerchantUser user)
    {
        if (user != null && StringUtils.equals(userName, user.getUsername()))
        {
            return loginUserToMerchantUserOnline(user);
        }
        return null;
    }

    @Override
    public SysUserOnline selectMerchantOnlineByInfo(String ipaddr, String userName, LoginMerchantUser user)
    {
        if (user != null)
        {
            if (StringUtils.isNotEmpty(ipaddr) && StringUtils.isNotEmpty(userName))
            {
                if (StringUtils.equals(ipaddr, user.getIpaddr()) && StringUtils.equals(userName, user.getUsername()))
                {
                    return loginUserToMerchantUserOnline(user);
                }
            }
            else if (StringUtils.isNotEmpty(ipaddr))
            {
                if (StringUtils.equals(ipaddr, user.getIpaddr()))
                {
                    return loginUserToMerchantUserOnline(user);
                }
            }
            else if (StringUtils.isNotEmpty(userName))
            {
                if (StringUtils.equals(userName, user.getUsername()))
                {
                    return loginUserToMerchantUserOnline(user);
                }
            }
            else
            {
                return loginUserToMerchantUserOnline(user);
            }
        }
        return null;
    }

    /**
     * 获取商户在线用户列表
     * 
     * @param ipaddr IP地址
     * @param userName 用户名
     * @return 在线商户用户列表
     */
    public List<SysUserOnline> selectMerchantOnlineList(String ipaddr, String userName)
    {
        // 获取商户登录token的所有key
        Collection<String> keys = redisCache.keys(CacheConstants.MERCHANT_LOGIN_TOKEN_KEY + "*");
        List<SysUserOnline> userOnlineList = new ArrayList<>();
        
        for (String key : keys)
        {
            LoginMerchantUser user = redisCache.getCacheObject(key);
            if (user != null)
            {
                SysUserOnline sysUserOnline = selectMerchantOnlineByInfo(ipaddr, userName, user);
                if (sysUserOnline != null)
                {
                    userOnlineList.add(sysUserOnline);
                }
            }
        }
        return userOnlineList;
    }

    /**
     * 获取商户在线用户数量
     * 
     * @return 在线商户用户数量
     */
    public int getMerchantOnlineCount()
    {
        Collection<String> keys = redisCache.keys(CacheConstants.MERCHANT_LOGIN_TOKEN_KEY + "*");
        int count = 0;
        for (String key : keys)
        {
            LoginMerchantUser user = redisCache.getCacheObject(key);
            if (user != null)
            {
                count++;
            }
        }
        return count;
    }

    /**
     * 根据token强制商户用户下线
     * 
     * @param tokenId 令牌ID
     */
    public void forceMerchantLogout(String tokenId)
    {
        String key = CacheConstants.MERCHANT_LOGIN_TOKEN_KEY + tokenId;
        redisCache.deleteObject(key);
    }

    @Override
    public SysUserOnline loginUserToMerchantUserOnline(LoginMerchantUser user)
    {
        if (user == null)
        {
            return null;
        }
        
        SysUserOnline sysUserOnline = new SysUserOnline();
        sysUserOnline.setTokenId(user.getToken());
        sysUserOnline.setUserName(user.getUsername());
        sysUserOnline.setIpaddr(user.getIpaddr());
        sysUserOnline.setLoginLocation(user.getLoginLocation());
        sysUserOnline.setBrowser(user.getBrowser());
        sysUserOnline.setOs(user.getOs());
        sysUserOnline.setLoginTime(user.getLoginTime());
        return sysUserOnline;
    }
}