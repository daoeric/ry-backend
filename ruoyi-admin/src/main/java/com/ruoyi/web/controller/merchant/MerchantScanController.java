package com.ruoyi.web.controller.merchant;

import com.ruoyi.business.domain.*;
import com.ruoyi.business.service.*;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginMerchantUser;
import com.ruoyi.common.core.domain.model.ScanBody;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.dto.merchant.BankInfoAddDto;
import com.ruoyi.common.dto.merchant.WithdrawAddDto;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.payment.DepositResult;
import com.ruoyi.common.utils.RedisLock;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 商户端登录注册
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/merchant")
public class MerchantScanController extends BaseController
{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private ITScanOrderService scanOrderService;

    @Autowired
    private ITVipService vipService;

    @Autowired
    private RedisLock redisLock;

    @Autowired
    private ITPaymentRequestService paymentRequestService;

    @Autowired
    private ITCustomerService customerService;

    @Autowired
    private ITBankInfoService bankInfoService;

    @Autowired
    private ITWithdrawRequestService withdrawRequestService;



    @PostMapping("/scan")
    public AjaxResult scan(@RequestBody @Validated ScanBody scanBody)
    {
        //根据token获取登录用户的信息
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        String barcode = scanBody.getBarcode();
        Long userId = loginUser.getId();
        String key = "scan:"+userId;
        TScanOrder result = null;
        //查看barCode是否重复
        TScanOrder existBarcode = scanOrderService.selectTScanOrderByBarcode(barcode);
        if (existBarcode!=null){
            throw new CustomException("Barcode was existed!");
        }
        try{
            if (redisLock.tryLock(key, 5, 60, TimeUnit.SECONDS)) {
                result = scanOrderService.scan(userId,barcode);
            }
        } catch (Exception e){
            return AjaxResult.error(e.getMessage());
        }
        return result!=null?AjaxResult.success(result):AjaxResult.errorByCode("操作失败");
    }

    /**
     * 查询扫描订单列表
     */
    @GetMapping("/scan/list")
    public TableDataInfo list(TScanOrder tScanOrder)
    {
        startPage();
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        tScanOrder.setCustomerId(loginUser.getId());
        List<TScanOrder> list = scanOrderService.selectTScanOrderList(tScanOrder);
        return getDataTable(list);
    }

    /**
     * 存款接口
     */
    @PostMapping("/deposit")
    public AjaxResult deposit(@RequestBody @Validated TPaymentRequest paymentRequest)
    {
        //根据token获取登录用户的信息
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        Long userId = loginUser.getId();
        
        // 设置用户ID
        paymentRequest.setCustomerId(userId);
        paymentRequest.setUsername(loginUser.getUsername());
        DepositResult result = paymentRequestService.deposit(userId,loginUser.getUsername(),paymentRequest.getOrderAmount());
        if (result.getCode()==200) {
            return AjaxResult.success(result);
        } else {
            return AjaxResult.error("failed pull deposit");
        }
    }

    /**
     * 提款接口
     */
    @PostMapping("/withdraw")
    public AjaxResult withdraw(@RequestBody @Validated WithdrawAddDto dto)
    {
        //根据token获取登录用户的信息
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        Long userId = loginUser.getId();
        boolean result = withdrawRequestService.withdraw(userId,dto.getWithdrawAmount(),dto.getBankInfoId());
        return result? AjaxResult.success("success") : AjaxResult.error("failed pull withdraw");
    }



    /**
     * 查询存款记录列表
     */
    @GetMapping("/deposit/list")
    public TableDataInfo depositList(TPaymentRequest paymentRequest)
    {
        startPage();
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        paymentRequest.setCustomerId(loginUser.getId());
        List<TPaymentRequest> list = paymentRequestService.selectTPaymentRequestList(paymentRequest);
        return getDataTable(list);
    }

    /**
     * 查询提款记录列表
     */
    @GetMapping("/withdraw/list")
    public TableDataInfo withdrawList(TWithdrawRequest withdrawRequest)
    {
        startPage();
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        withdrawRequest.setCustomerId(loginUser.getId());
        List<TWithdrawRequest> list = withdrawRequestService.selectTWithdrawRequestList(withdrawRequest);
        return getDataTable(list);
    }

//    @PostMapping("/pay")
//    public AjaxResult pay(@RequestBody @Validated DepositDto DepositDto)
//    {
//        //根据token获取登录用户的信息
//        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
//        Long userId = loginUser.getId();
//
//        DepositDto.setUserId(userId);
//
//        DepositResult result = paymentRequestService.pay(DepositDto);
//
//        return AjaxResult.success(result);
//
//
//    }


    @GetMapping("/user/bankCards")
    public AjaxResult bankCards()
    {
        //根据token获取登录用户的信息
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        Long userId = loginUser.getId();
        TBankInfo bankInfo = new TBankInfo();
        bankInfo.setCustomerId(userId);
        List<TBankInfo> bankInfoList = bankInfoService.selectTBankInfoList(bankInfo);
        return AjaxResult.success(bankInfoList);
    }

    @GetMapping("/user/vips")
    public AjaxResult vips()
    {
        List<TVip> vipList = vipService.selectUpdateVip();
        return AjaxResult.success(vipList);
    }

    @PostMapping("/user/bankCard")
    public AjaxResult bankCardsPost(@RequestBody @Validated BankInfoAddDto addDto)
    {
        //根据token获取登录用户的信息
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        Long userId = loginUser.getId();
        //查看用户是否绑定了实名，如果没有，则提示先绑定实名
        TCustomer customer = customerService.getById(userId);
        if (StringUtils.isEmpty(customer.getRealName())) {
            throw new CustomException("请先进行实名操作！");
        }

        //查看是否达到绑定银行卡的次数
        Integer count = bankInfoService.countByCustomerId(userId);
        int limit  = 5;
        if (count >= 5) {
            throw new CustomException("银达到绑定银行卡的次数"+limit+"次，请先解绑！");
        }

        TBankInfo bankInfo = new TBankInfo();
        bankInfo.setCustomerId(userId);
        bankInfo.setBankName(addDto.getBankName());
        bankInfo.setBankCard(addDto.getCardNumber());
        bankInfo.setRealName(customer.getRealName());
        bankInfo.setUsername(loginUser.getUsername());
        int result = bankInfoService.insertTBankInfo(bankInfo);
        return AjaxResult.success(result>0);
    }

    @DeleteMapping("/user/bankCard/{id}")
    public AjaxResult bankCardsPost(@PathVariable("id") Long id)
    {
        //根据token获取登录用户的信息
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        Long userId = loginUser.getId();

        TBankInfo bankInfo = bankInfoService.getById(id);
        if (bankInfo == null) {
            throw new CustomException("参数错误");
        }
        if (!bankInfo.getCustomerId().equals(userId)) {
            throw new CustomException("参数错误");
        }
        int count = bankInfoService.deleteTBankInfoById(id);
        return AjaxResult.success(count>0);
    }

}
