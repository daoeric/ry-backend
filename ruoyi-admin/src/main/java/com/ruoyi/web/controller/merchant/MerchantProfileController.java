package com.ruoyi.web.controller.merchant;

import com.ruoyi.business.domain.TCreditLog;
import com.ruoyi.business.domain.TCustomer;
import com.ruoyi.business.domain.TRealnameAuth;
import com.ruoyi.business.service.ITCreditLogService;
import com.ruoyi.business.service.ITCustomerService;
import com.ruoyi.business.service.ITRealnameAuthService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginMerchantUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.common.dto.ChangePasswordDto;
import com.ruoyi.common.dto.RealNameAuthDto;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.vo.merchant.CustomerVO;
import com.ruoyi.common.vo.merchant.IndexVO;
import com.ruoyi.common.vo.merchant.ScrollerVO;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商户端登录注册
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/merchant")
public class MerchantProfileController extends BaseController
{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private ITCustomerService customerService;
    
    @Autowired
    private ITRealnameAuthService realnameAuthService;

    @Autowired
    private ITCreditLogService creditLogService;

    @Autowired
    private RedisCache redisCache;


    @GetMapping("/myProfile")
    public AjaxResult myProfile()
    {
        //根据token获取登录用户的信息
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        TCustomer customer = customerService.getById(loginUser.getId());
        CustomerVO customerVO = new CustomerVO();
        BeanUtils.copyProperties(customer, customerVO);
        return AjaxResult.success(customerVO);
    }

    @GetMapping("/creditLog/list")
    public TableDataInfo list(TCreditLog tCreditLog)
    {
        startPage();
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        tCreditLog.setCustomerId(loginUser.getId());
        List<TCreditLog> list = creditLogService.selectTCreditLogList(tCreditLog);
        return getDataTable(list);
    }

    @GetMapping("/index")
    public AjaxResult list()
    {
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        IndexVO vo = new IndexVO();
        vo.setUserId(loginUser.getId());
        TCustomer customer = customerService.getById(loginUser.getId());
        vo.setUsername(customer.getUsername());
        String pool = redisCache.getCacheObject(Constants.POOL_KEY);
        String rewards = redisCache.getCacheObject(Constants.REWARDS_KEY);
        vo.setPool(new BigDecimal(pool));
        vo.setTotalRewards(new BigDecimal(rewards));
        return AjaxResult.success(vo);
    }

    @GetMapping("/scroller/list")
    public AjaxResult scrollerList()
    {
//        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        //先从redis中获取缓存数据
        String key = "merchant:scroller";
        List<ScrollerVO> scrollerVOList = redisCache.getCacheList(key);
        return AjaxResult.success(scrollerVOList);
    }


    /**
     * 修改密码
     */
    @Log(title = "客户修改密码", businessType = BusinessType.UPDATE)
    @PutMapping("/changePwd")
    public AjaxResult changePwd(@Validated @RequestBody ChangePasswordDto passwordDto)
    {
        // 校验新密码和确认密码是否一致
        if (!passwordDto.getNewPassword().equals(passwordDto.getConfirmNewPassword())) {
            return AjaxResult.errorByCode("merchant.change.password.confirm.error");
        }

        // 获取当前登录用户
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        
        // 查询用户信息
        TCustomer customer = customerService.getById(loginUser.getId());
        if (customer == null) {
            return AjaxResult.errorByCode("user.not.exists");
        }

        // 校验旧密码是否正确
        if (!SecurityUtils.matchesPassword(passwordDto.getOldPassword(), customer.getPassword())) {
            return AjaxResult.errorByCode("merchant.change.password.old.error");
        }

        // 校验新密码不能与旧密码相同
        if (SecurityUtils.matchesPassword(passwordDto.getNewPassword(), customer.getPassword())) {
            return AjaxResult.errorByCode("merchant.change.password.same.error");
        }
        // 更新密码
        customer.setPassword(SecurityUtils.encryptPassword(passwordDto.getNewPassword()));
        boolean result = customerService.updatePwd(customer.getId(),passwordDto.getNewPassword());
        return result ? AjaxResult.successByCode("merchant.change.password.success") : AjaxResult.errorByCode("merchant.change.password.error");
    }
    
    /**
     * 实名认证
     */
    @Log(title = "商户实名认证", businessType = BusinessType.UPDATE)
    @PostMapping(value = "/realname/auth")
    public AjaxResult realnameAuth(@Validated @RequestBody RealNameAuthDto realNameAuthDto)
    {
        String realName =  realNameAuthDto.getRealName();
        String phoneNumber = realNameAuthDto.getPhoneNumber();

        // 获取当前登录用户
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        
        // 查询用户信息
        TCustomer customer = customerService.getById(loginUser.getId());
        if (customer == null) {
            return AjaxResult.errorByCode("user.not.exists");
        }
        
        // 检查用户是否已经认证通过
        if (customer.getRealnameStatus() != null && customer.getRealnameStatus() == 2) {
            return AjaxResult.errorByCode("merchant.realname.auth.duplicate.error");
        }
        
        // 检查是否已有待审核的认证记录
        TRealnameAuth existingAuth = realnameAuthService.selectByCustomerId(customer.getId());
        if (existingAuth != null && existingAuth.getStatus() == 0) {
            return AjaxResult.errorByCode("merchant.realname.auth.pending.error");
        }
        
        // 创建实名认证记录
        TRealnameAuth realnameAuth = new TRealnameAuth();
        realnameAuth.setCustomerId(customer.getId());
        realnameAuth.setRealName(realName);
        realnameAuth.setPhoneNumber(phoneNumber);
        realnameAuth.setTelegramId(realNameAuthDto.getTelegramId());
        realnameAuth.setWhatsappId(realNameAuthDto.getWhatsappId());
        realnameAuth.setStatus(0); // 待审核状态
        
        // 保存实名认证记录
        boolean result = realnameAuthService.save(realnameAuth);
        
        if (result) {
            // 更新用户实名认证状态为待审核
            customerService.updateRealnameStatus(customer.getId(), 1);
            return AjaxResult.successByCode("merchant.realname.auth.success");
        } else {
            return AjaxResult.errorByCode("merchant.realname.auth.error");
        }
    }


    /**
     *  获取认证状态
     */
    @GetMapping("/realname/auth")
    public AjaxResult realnameStatus()
    {
        // 获取当前登录用户
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        // 查询用户信息
        TCustomer customer = customerService.getById(loginUser.getId());
        if (customer == null) {
            return AjaxResult.error("user.not.exists");
        }
        AjaxResult ajax = AjaxResult.success();
        ajax.put("realnameStatus", customer.getRealnameStatus());
        return ajax;
    }
}