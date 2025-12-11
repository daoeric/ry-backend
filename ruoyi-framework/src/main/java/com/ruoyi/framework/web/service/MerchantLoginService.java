package com.ruoyi.framework.web.service;

import com.ruoyi.business.domain.TCustomer;
import com.ruoyi.business.service.ITCustomerService;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.core.domain.model.LoginMerchantUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 登录校验
 */
@Component
public class MerchantLoginService {

    @Autowired
    private ITCustomerService customerService;

    @Autowired
    private RedisCache redisCache;

    @Value(value = "${user.password.maxRetryCount}")
    private int maxRetryCount;

    @Value(value = "${user.password.lockTime}")
    private int lockTime;


    public LoginMerchantUser login(String username, String password){
        //验证用户名是否存在
        TCustomer customer = customerService.selectTCustomerByUsername(username);
        if (customer == null){
            throw new BaseException("用户名错误");
        }
        //密码错误校验
        validatePasswordRetry(customer, password);
        if (customer.getStatus().equals(1)){
            throw new BaseException("账户已停用");
        }
        //记录登录信息
        recordLoginInfo(customer.getId());
        LoginMerchantUser loginMerchantUser = new LoginMerchantUser();
        BeanUtils.copyBeanProp(loginMerchantUser,customer);
        //生成token
        loginMerchantUser.setToken("customer:token:" + IdUtils.fastUUID());
        //根据token缓存loginMerchantUser
        redisCache.setCacheObject(loginMerchantUser.getToken(),loginMerchantUser,1, TimeUnit.DAYS);
        return loginMerchantUser;
    }

    /**
     * 记录登录信息
     * @param id
     */
    public void recordLoginInfo(Long id){
        TCustomer tCustomer = new TCustomer();
        tCustomer.setId(id);
        tCustomer.setLastLoginAddress(IpUtils.getIpAddr());
        tCustomer.setLastLoginTime(DateUtils.getNowDate());
        customerService.updateTCustomer(tCustomer);
    }

    /**
     * 校验密码错误次数，超过限制锁定一定时间
     */
    private void validatePasswordRetry(TCustomer customer, String rawPassword) {
        String username = customer.getUsername();
        Integer retryCount = redisCache.getCacheObject(getCacheKey(username));
        if (retryCount == null) {
            retryCount = 0;
        }
        if (retryCount >= Integer.valueOf(maxRetryCount)) {
            throw new BaseException("密码错误次数过多，账户已锁定" + lockTime + "分钟");
        }

        if (!SecurityUtils.matchesPassword(rawPassword, customer.getPassword())) {
            retryCount = retryCount + 1;
            redisCache.setCacheObject(getCacheKey(username), retryCount, lockTime, TimeUnit.MINUTES);
            int remain = Math.max(maxRetryCount - retryCount, 0);
            if (remain == 0) {
                throw new BaseException("密码错误次数过多，账户已锁定" + lockTime + "分钟");
            }
            throw new BaseException("密码错误，还剩" + remain + "次机会");
        }
        clearLoginRecordCache(username);
    }

    private String getCacheKey(String username) {
        return CacheConstants.PWD_ERR_CNT_KEY + username;
    }

    private void clearLoginRecordCache(String username) {
        if (redisCache.hasKey(getCacheKey(username))) {
            redisCache.deleteObject(getCacheKey(username));
        }
    }
}
