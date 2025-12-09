package com.ruoyi.web.controller.merchant;

import com.ruoyi.business.domain.TCustomer;
import com.ruoyi.business.service.ITCustomerService;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginBody;
import com.ruoyi.common.core.domain.model.LoginMerchantUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.SysLoginService;
import com.ruoyi.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

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
    private SysLoginService loginService;

    @Autowired
    private ITCustomerService customerService;

    @Autowired
    private ISysConfigService configService;

    @Value("${ruoyi.name}")
    private String projectName;

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
        LoginMerchantUser loginUser = (LoginMerchantUser) loginService.login(
                loginBody.getUsername(), 
                loginBody.getPassword(), 
                loginBody.getCode(),
                loginBody.getUuid()
        );
        
        // 判断是否需要返回谷歌验证码
        if(StringUtils.isEmpty(loginBody.getCode())){
            ajax.put("safeMode", loginUser.getLastLoginTime() == null ? 0 : 1);
            ajax.put("googleCode", "otpauth://totp/" + projectName + "@" + loginUser.getUsername() 
                    + "?secret=" + loginUser.getGoogleCode());
        }
        
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
        // 检查是否开启商户注册功能
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerMerchant"))))
        {
            return error("当前系统没有开启商户注册功能！");
        }

        // 验证用户名是否已存在
        TCustomer existCustomer = customerService.selectTCustomerByUsername(registerBody.getUsername());
        if (existCustomer != null)
        {
            return error("注册失败，用户名已存在");
        }


        // 创建商户账号
        TCustomer customer = new TCustomer();
        customer.setUsername(registerBody.getUsername());
        customer.setPassword(SecurityUtils.encryptPassword(registerBody.getPassword()));

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
                return AjaxResult.success("注册成功");
            }
            else
            {
                return error("注册失败，请联系系统管理员");
            }
        }
        catch (Exception e)
        {
            logger.error("商户注册异常", e);
            return error("注册失败：" + e.getMessage());
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
            return error("商户信息不存在");
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
    public static class MerchantRegisterBody
    {
        /**
         * 用户名
         */
        @NotBlank(message = "用户名不能为空")
        @Size(min = 6, max = 20, message = "用户名长度必须在6到20个字符之间")
        private String username;

        /**
         * 密码
         */
        @NotBlank(message = "密码不能为空")
        @Size(min = 6, max = 20, message = "密码长度必须在6到20个字符之间")
        private String password;

        /**
         * 确认密码
         */
        @NotBlank(message = "确认密码不能为空")
        private String confirmPassword;

        /**
         * 邮箱
         */
        private String email;

        /**
         * 手机号
         */
        private String phone;

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
