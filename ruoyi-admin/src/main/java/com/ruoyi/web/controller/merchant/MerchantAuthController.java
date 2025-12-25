package com.ruoyi.web.controller.merchant;

import com.ruoyi.business.domain.TCustomer;
import com.ruoyi.business.service.ITCustomerService;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.core.domain.model.LoginMerchantUser;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.utils.Base62;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.uuid.SnowflakeKeyGenerator;
import com.ruoyi.common.vo.merchant.ScrollerVO;
import com.ruoyi.framework.web.service.MerchantLoginService;
import com.ruoyi.system.service.ISysConfigService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

/**
 * 商户端登录注册
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/merchant")
public class MerchantAuthController extends BaseController
{
    @Autowired
    private MerchantLoginService loginService;

    @Autowired
    private ITCustomerService customerService;

    @Autowired
    private ISysConfigService configService;

    @Autowired
    private SnowflakeKeyGenerator snowflakeKeyGenerator;

    @Autowired
    private RedisCache redisCache;

    @GetMapping("/test")
    public AjaxResult test()
    {
        String key = "merchant:scroller";
        ScrollerVO vo =new ScrollerVO();
        vo.setRewards(new BigDecimal("10.88"));
        vo.setUserId(2L);
        vo.setUsername("test8899");
        redisCache.lLeftPush(key,vo);
        return AjaxResult.success("test");
    }


    /**
     * 商户登录方法
     * 
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody @Validated LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        LoginMerchantUser loginUser = loginService.login(
                loginBody.getUsername(),
                loginBody.getPassword()
        );
        ajax.put("username",loginBody.getUsername());
        ajax.put(Constants.TOKEN, loginUser.getToken());
        return ajax;
    }

    /**
     * 商户注册
     * 
     * @param registerBody 注册信息
     * @return 结果
     */
    @PostMapping("/register")
    public AjaxResult register(@RequestBody @Validated MerchantRegisterBody registerBody)
    {
        //校验密码是否一致
        if (!registerBody.getPassword().equals(registerBody.getConfirmPassword())){
            return AjaxResult.errorByCode("merchant.register.password.mismatch");
        }
        // 验证用户名是否已存在
        TCustomer existCustomer = customerService.selectTCustomerByUsername(registerBody.getUsername());
        if (existCustomer != null)
        {
            return AjaxResult.errorByCode("merchant.register.username.exists");
        }
        // 查看邀请码是否已存在
        TCustomer existInviteCode = customerService.selectTCustomerByInviteCode(registerBody.getInviteCode());
        if (existInviteCode == null)
        {
            return AjaxResult.errorByCode("merchant.register.invite.code.not.exists");
        }

        // 创建商户账号
        TCustomer customer = new TCustomer();
        customer.setUsername(registerBody.getUsername());
        customer.setPassword(SecurityUtils.encryptPassword(registerBody.getPassword()));
        //设置邀请人id
        customer.setPId(existInviteCode.getId());
        // 初始化默认值
        customer.setBalance(BigDecimal.ZERO);
        customer.setLockBalance(BigDecimal.ZERO);
        customer.setLockBalance(BigDecimal.ZERO);
        customer.setStatus(0); // 0-正常 1-禁用
        customer.setGrade(1);


        long id = snowflakeKeyGenerator.generateKey();   // 雪花算法 64-bit
        String code = Base62.encode(id);
        //随机全局生成唯一邀请码
        customer.setInviteCode(code);



        try
        {
            int result = customerService.insertTCustomer(customer);
            if (result > 0)
            {
                return AjaxResult.successByCode("merchant.register.success");
            }
            else
            {
                return AjaxResult.errorByCode("merchant.register.error");
            }
        }
        catch (Exception e)
        {
            logger.error("商户注册异常", e);
                return AjaxResult.errorByCode("merchant.register.error.detail", e.getMessage());
        }
    }

    /**
     * 获取商户信息
     * 
     * @return 商户信息
     */
    @GetMapping("/getInfo")
    public AjaxResult getInfo()
    {
        LoginMerchantUser loginUser = (LoginMerchantUser) SecurityUtils.getLoginUser();
        TCustomer customer = customerService.getById(loginUser.getId());
        
        if (customer == null)
        {
            return AjaxResult.errorByCode("merchant.info.not.exists");
        }
        
        // 清除敏感信息
        customer.setPassword(null);
        customer.setWithdrawPassword(null);

        AjaxResult ajax = AjaxResult.success();
        ajax.put("merchant", customer);
        return ajax;
    }

    /**
     * 商户注册请求体
     */
    @Validated
    @Data
    public static class MerchantRegisterBody
    {
        /**
         * 用户名
         */
        @NotBlank(message = "{merchant.register.username.not.blank}")
        @Size(min = 6, max = 20, message = "{merchant.register.username.length}")
        private String username;

        /**
         * 密码
         */
        @NotBlank(message = "{merchant.register.password.not.blank}")
        @Size(min = 6, max = 20, message = "{merchant.register.password.length}")
        private String password;

        /**
         * 确认密码
         */
        @NotBlank(message = "{merchant.register.confirm.password.not.blank}")
        private String confirmPassword;

        /**
         * 邮箱
         */
        private String email;

        /**
         * 手机号
         */
        private String phone;
        /**
         * 邀请码
         */
        @NotBlank(message = "{merchant.register.invite.code.not.blank}")
        private String inviteCode;

        public String getUsername()
        {
            return username;
        }

        public void setUsername(String username)
        {
            this.username = username;
        }

        public String getPassword()
        {
            return password;
        }

        public void setPassword(String password)
        {
            this.password = password;
        }

        public String getConfirmPassword()
        {
            return confirmPassword;
        }

        public void setConfirmPassword(String confirmPassword)
        {
            this.confirmPassword = confirmPassword;
        }

        public String getEmail()
        {
            return email;
        }

        public void setEmail(String email)
        {
            this.email = email;
        }

        public String getPhone()
        {
            return phone;
        }

        public void setPhone(String phone)
        {
            this.phone = phone;
        }
    }
}
