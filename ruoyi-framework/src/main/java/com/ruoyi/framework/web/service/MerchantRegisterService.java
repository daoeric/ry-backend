package com.ruoyi.framework.web.service;

import com.ruoyi.business.domain.TCustomer;
import com.ruoyi.business.service.ITCustomerService;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.model.MerchantRegisterBody;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;
import com.ruoyi.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 商户注册校验方法
 * 
 * @author ruoyi
 */
@Component
public class MerchantRegisterService
{
    @Autowired
    private ITCustomerService customerService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 商户注册
     * 
     * @param registerBody 注册信息
     * @return 结果消息
     */
    public String register(MerchantRegisterBody registerBody)
    {
        String msg = "";
        String username = registerBody.getUsername();
        String password = registerBody.getPassword();
        String confirmPassword = registerBody.getConfirmPassword();

        // 基础验证
        if (StringUtils.isEmpty(username))
        {
            msg = "用户名不能为空";
        }
        else if (StringUtils.isEmpty(password))
        {
            msg = "密码不能为空";
        }
        else if (StringUtils.isEmpty(confirmPassword))
        {
            msg = "确认密码不能为空";
        }
        else if (!password.equals(confirmPassword))
        {
            msg = "两次输入的密码不一致";
        }
        else if (username.length() < 6 || username.length() > 20)
        {
            msg = "用户名长度必须在6到20个字符之间";
        }
        else if (password.length() < 6 || password.length() > 20)
        {
            msg = "密码长度必须在6到20个字符之间";
        }
        // 检查用户名是否已存在
        else if (customerService.selectTCustomerByUsername(username)!=null)
        {
            msg = "注册失败，用户名 '" + username + "' 已存在";
        }

        // 如果验证都通过，则创建商户
        if (StringUtils.isEmpty(msg))
        {
            TCustomer customer = new TCustomer();
            customer.setUsername(username);
            customer.setPassword(SecurityUtils.encryptPassword(password));

            // 初始化默认值
            customer.setBalance(BigDecimal.ZERO);
            customer.setLockBalance(BigDecimal.ZERO);
            customer.setLockBalance(BigDecimal.ZERO);
            customer.setStatus(0); // 0-正常 1-禁用
            customer.setGrade(1);
            try
            {
                int result = customerService.insertTCustomer(customer);
                if (result > 0)
                {
                    // 记录注册日志
                    AsyncManager.me().execute(AsyncFactory.recordLogininfor(
                            username, 
                            Constants.REGISTER, 
                            "商户注册成功"
                    ));
                }
                else
                {
                    msg = "注册失败，请联系系统管理员";
                }
            }
            catch (Exception e)
            {
                msg = "注册异常：" + e.getMessage();
            }
        }
        
        return msg;
    }
}
