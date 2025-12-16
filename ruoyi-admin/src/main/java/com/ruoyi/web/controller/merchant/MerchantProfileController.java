package com.ruoyi.web.controller.merchant;

import com.ruoyi.business.domain.TCreditLog;
import com.ruoyi.business.domain.TCustomer;
import com.ruoyi.business.domain.TRealnameAuth;
import com.ruoyi.business.service.ITCreditLogService;
import com.ruoyi.business.service.ITCustomerService;
import com.ruoyi.business.service.ITRealnameAuthService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginMerchantUser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.dto.ChangePasswordDto;
import com.ruoyi.common.dto.RealNameAuthDto;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.vo.merchant.CustomerVO;
import com.ruoyi.common.vo.merchant.IndexVO;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    private ServerConfig serverConfig;



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
        vo.setPool(new BigDecimal("14230.55"));
        vo.setTotalRewards(new BigDecimal("8932"));
        return AjaxResult.success(vo);
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
            return AjaxResult.error("新密码与确认密码不一致");
        }

        // 获取当前登录用户
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        
        // 查询用户信息
        TCustomer customer = customerService.getById(loginUser.getId());
        if (customer == null) {
            return AjaxResult.error("用户不存在");
        }

        // 校验旧密码是否正确
        if (!SecurityUtils.matchesPassword(passwordDto.getOldPassword(), customer.getPassword())) {
            return AjaxResult.error("旧密码错误");
        }

        // 校验新密码不能与旧密码相同
        if (SecurityUtils.matchesPassword(passwordDto.getNewPassword(), customer.getPassword())) {
            return AjaxResult.error("新密码不能与旧密码相同");
        }
        // 更新密码
        customer.setPassword(SecurityUtils.encryptPassword(passwordDto.getNewPassword()));
        boolean result = customerService.updatePwd(customer.getId(),passwordDto.getNewPassword());
        return result ? AjaxResult.success("密码修改成功") : AjaxResult.error("密码修改失败");
    }
    
    /**
     * 实名认证
     */
    @Log(title = "商户实名认证", businessType = BusinessType.UPDATE)
    @PostMapping("/realname/auth")
    public AjaxResult realnameAuth(@Validated @RequestBody RealNameAuthDto realNameAuthDto)
    {
        // 获取当前登录用户
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        
        // 查询用户信息
        TCustomer customer = customerService.getById(loginUser.getId());
        if (customer == null) {
            return AjaxResult.error("用户不存在");
        }
        
        // 检查用户是否已经认证通过
        if (customer.getRealnameStatus() != null && customer.getRealnameStatus() == 2) {
            return AjaxResult.error("您已完成实名认证，无需重复提交");
        }
        
        // 检查是否已有待审核的认证记录
        TRealnameAuth existingAuth = realnameAuthService.selectByCustomerId(customer.getId());
        if (existingAuth != null && existingAuth.getStatus() == 0) {
            return AjaxResult.error("您已提交实名认证申请，请等待审核");
        }
        
        // 创建实名认证记录
        TRealnameAuth realnameAuth = new TRealnameAuth();
        realnameAuth.setCustomerId(customer.getId());
        realnameAuth.setRealName(realNameAuthDto.getRealName());
        realnameAuth.setIdCardFront(realNameAuthDto.getIdCardFront());
        realnameAuth.setIdCardBack(realNameAuthDto.getIdCardBack());
        realnameAuth.setStatus(0); // 待审核状态
        
        // 保存实名认证记录
        boolean result = realnameAuthService.save(realnameAuth);
        
        if (result) {
            // 更新用户实名认证状态为待审核
            customerService.updateRealnameStatus(customer.getId(), 1);
            return AjaxResult.success("实名认证申请已提交，请等待审核");
        } else {
            return AjaxResult.error("实名认证申请提交失败");
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
            return AjaxResult.error("用户不存在");
        }
        AjaxResult ajax = AjaxResult.success();
        ajax.put("realnameStatus", customer.getRealnameStatus());
        return ajax;
    }

    @PostMapping("/common/upload")
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
            ajax.put("fileName", fileName);
            ajax.put("newFileName", FileUtils.getName(fileName));
            ajax.put("originalFilename", file.getOriginalFilename());
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }
}