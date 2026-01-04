package com.ruoyi.web.controller.merchant;

import com.ruoyi.business.domain.TPaymentRequest;
import com.ruoyi.business.domain.TScanOrder;
import com.ruoyi.business.service.ITCustomerService;
import com.ruoyi.business.service.ITPaymentRequestService;
import com.ruoyi.business.service.ITScanOrderService;
import com.ruoyi.business.service.ITVipService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginMerchantUser;
import com.ruoyi.common.core.domain.model.ScanBody;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.dto.payment.DepositDto;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.payment.DepositResult;
import com.ruoyi.common.utils.RedisLock;
import com.ruoyi.common.utils.ServletUtils;
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

    @PostMapping("/pay")
    public AjaxResult pay(@RequestBody @Validated DepositDto DepositDto)
    {
        //根据token获取登录用户的信息
        LoginMerchantUser loginUser = (LoginMerchantUser) tokenService.getLoginUser(ServletUtils.getRequest());
        Long userId = loginUser.getId();

        DepositDto.setUserId(userId);

        DepositResult result = paymentRequestService.pay(DepositDto);

        return AjaxResult.success(result);


    }





}
