package com.ruoyi.web.controller.merchant;

import com.ruoyi.business.domain.TCreditLog;
import com.ruoyi.business.domain.TCustomer;
import com.ruoyi.business.domain.TScanOrder;
import com.ruoyi.business.service.ITCreditLogService;
import com.ruoyi.business.service.ITCustomerService;
import com.ruoyi.business.service.ITScanOrderService;
import com.ruoyi.business.service.ITVipService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginMerchantUser;
import com.ruoyi.common.core.domain.model.ScanBody;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.RedisLock;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.vo.merchant.CustomerVO;
import com.ruoyi.common.vo.merchant.IndexVO;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private ITCreditLogService creditLogService;



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

}
