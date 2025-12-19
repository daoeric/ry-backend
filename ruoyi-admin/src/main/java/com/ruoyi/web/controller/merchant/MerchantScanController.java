package com.ruoyi.web.controller.merchant;

import com.ruoyi.business.domain.TScanOrder;
import com.ruoyi.business.service.ITScanOrderService;
import com.ruoyi.business.service.ITVipService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.LoginMerchantUser;
import com.ruoyi.common.core.domain.model.ScanBody;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.RedisLock;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.SQLIntegrityConstraintViolationException;
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
        return result!=null?AjaxResult.success(result):AjaxResult.error("操作失败");
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

}
