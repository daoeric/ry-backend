package com.ruoyi.payment.impl;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.business.domain.TChannelRujin;
import com.ruoyi.business.domain.TWithdrawRequest;
import com.ruoyi.business.service.ITChannelRujinService;
import com.ruoyi.common.dto.payment.DepositDto;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.payment.DepositResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.OkHttpUtils;
import com.ruoyi.payment.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;



@Slf4j
@Service("lppay")
public class LPPayServiceImpl implements IPaymentService {
    private static final String keyword = "lppay";

    @Autowired
    private ITChannelRujinService channelRujinService;

    @Override
    public boolean queryOrderStatus(TWithdrawRequest withdrawRequest) {
        return false;
    }

//    @Autowired
//    private ITChannelRujinService channelRujinService;
//
//    @Autowired
//    private ITChannelService channelService;
//
//    @Override
//    public boolean queryOrderStatus(TWithdrawRequest withdrawRequest) {
//        return false;
//    }
//
//    @Override
//    public WithdrawalResult withdrawal(WithdrawalDto dto,String requestId) throws Exception {
//        //获取配置的通道
//        TChannel channel = channelService.getWithdrawalChannel(keyword);
//        if (channel == null) {
//            throw new CustomException("通道没有配置");
//        }
//        WithdrawalResult withdrawalResult = new WithdrawalResult();
//        Map<String, Object> paramMap = new HashMap<>();
//
//        paramMap.put("app_id",channel.getMerchantId());
//        paramMap.put("product_id",channel.getChannelType());
//        paramMap.put("out_trade_no",requestId);
//        paramMap.put("notify_url",channel.getWithdrawalNotify());
//        paramMap.put("amount",dto.getAmount().toPlainString());
//        paramMap.put("time",String.valueOf(System.currentTimeMillis() / 1000));
//        paramMap.put("sign",sortSign(paramMap,channel.getPriKey()));
//
//        JSONObject paramJsonObject = new JSONObject();
//        paramJsonObject.put("bankName",dto.getBankName());
//        paramJsonObject.put("accountNumber",dto.getBankCardNo());
//        paramJsonObject.put("accountName",dto.getBankCardName());
//        paramJsonObject.put("ifsc",dto.getBankCode());
//        paramMap.put("ext",paramJsonObject);
//
//        // 发送支付请求
//        String result = OkHttpUtils.getInstance().doPostJson(channel.getApiUrl()+"api/payment", new JSONObject(paramMap).toJSONString());
//        log.info("八马代付发起，参数：{}，结果：{}", paramMap, result);
//        if (StringUtils.isNotEmpty(result)) {
//            JSONObject jsonObject = JSONObject.parseObject(result);
//            int code = jsonObject.getInteger("code");
//            String msg = jsonObject.getString("message");
//            JSONObject data = jsonObject.getJSONObject("data");
//
//            if (data != null && code==200) {//发起支付成功
//                withdrawalResult.setCode(200);
//                withdrawalResult.setMsg("发起代付成功");
//            }
//             else {//发起支付失败
//                withdrawalResult.setCode(500);
//                withdrawalResult.setMsg(msg);
//            }
//            withdrawalResult.setOrderNo(requestId);
//            return withdrawalResult;
//        } else {
//            //无法确定 上游那边情况，这里默认设置成代付成功，
//            withdrawalResult.setCode(200);
//            withdrawalResult.setError(true);
//            withdrawalResult.setMsg("超时");
//        }
//        return withdrawalResult;
//    }
//
    @Override
    public DepositResult deposit(DepositDto depositDto, String requestId, TChannelRujin channel) {
        DepositResult depositResult = new DepositResult();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("mchNo",channel.getMerchantId());
        paramMap.put("mchOrderId",requestId);
        paramMap.put("payType",channel.getChannelType());
        paramMap.put("notifyUrl",channel.getDepositNotify());
        paramMap.put("amount",depositDto.getPay_amount().toPlainString());
        paramMap.put("goodsName","SCAN");
        paramMap.put("signType","SHA512");
        paramMap.put("version","1.0");
        paramMap.put("sign",sortSign(paramMap,channel.getPriKey()));

        // 发送支付请求
        String result = OkHttpUtils.getInstance().doPostJson(channel.getApiUrl()+"gateway/order/create", new JSONObject(paramMap).toJSONString());
        log.info("八马支付发起，参数：{}，结果：{}", paramMap, result);
        if (StringUtils.isNotEmpty(result)) {
            JSONObject jsonObject = JSONObject.parseObject(result);
            Integer code = jsonObject.getInteger("code");
            JSONObject data = jsonObject.getJSONObject("data");
            String msg = jsonObject.getString("message");
            if (code==0 &&StringUtils.isNotEmpty(data.getString("payUrl"))) {//发起支付成功
                // 验签
                depositResult.setCode(200);
                depositResult.setPayUrl(data.getString("payUrl"));
                depositResult.setMsg("订单发起成功");
            } else {//发起支付失败
                depositResult.setCode(500);
                depositResult.setMsg(msg);
                depositResult.setResult(msg);
            }
            depositResult.setOrderNo(requestId);
            return depositResult;
        } else {
            depositResult.setCode(501);
            depositResult.setMsg("订单发起失败");
            depositResult.setResult("返回异常！");
        }
        return depositResult;
    }
//
//
    @Override
    public boolean depositCallback(Map<String, Object> parameterMap) throws Exception {
        TChannelRujin channelRujin = channelRujinService.getChannel(keyword);
        if (channelRujin == null) {
            log.info("八马代收回调没有可用的通道");
            throw new CustomException("代收回调通道没有配置");
        }
        String priKey = channelRujin.getPriKey();
        String sign = (String) parameterMap.get("sign");
        parameterMap.remove("sign");
        String checkSign = sortSign(parameterMap, priKey);
        if (StringUtils.equals(sign,checkSign)) {
            String state = parameterMap.get("payStatus").toString();
            if ("1".equals(state) ) {
                return true;
            }
        }
        return false;

    }
//
//    @Override
//    public WithdrawalCallbackResult withdrawalCallback(Map<String, Object> parameterMap) throws Exception {
//        TChannel channel = channelService.getWithdrawalChannel(keyword);
//        if (channel == null) {
//            log.info("八马代付回调没有可用的通道");
//            throw new CustomException("代付回调通道没有配置");
//        }
//        String status =  parameterMap.get("trade_status").toString();
//        //不加入签名
//        String sign = (String) parameterMap.get("sign");
//        String checkSign = sortSign(parameterMap, channel.getPriKey());
//        WithdrawalCallbackResult result = new WithdrawalCallbackResult();
//        result.setRealAmount(new BigDecimal((String) parameterMap.get("real_amount")));
//        result.setOrderNo(parameterMap.get("out_trade_no").toString());
//        if(StringUtils.equals(sign,checkSign)){
//            if("1".equals(status)){
//                log.info("八马代付回调成功：{}",parameterMap);
//                result.setStatus(2);
//            }else if("2".equals(status)){
//                log.info("八马代付回调失败：{}",parameterMap);
//                String msg = parameterMap.get("message") == null?"代付失败":parameterMap.get("message").toString();
//                result.setErrMsg(msg);
//                result.setStatus(3);
//            } else{
//                log.info("八马代付回调状态无需任何操作：{}",parameterMap);
//                return  null;
//            }
//            result.setSuccessMsg("success");
//        } else {
//            log.info("八马代付回调验签失败：{}",parameterMap);
//            result.setStatus(1);
//            result.setSuccessMsg("验签失败");
//        }
//        return result;
//    }
//
    @Override
    public String getOrderNo(Map<String, Object> parameterMap) {
        return parameterMap.get("mchOrderId").toString();
    }
//
    @Override
    public BigDecimal getRealAmount(Map<String, Object> parameterMap) {
        return null;
    }
//
    @Override
    public String getSuccessMsg() {
        return "success";
    }
//
//    @Override
//    public BalanceResult getBalance(Integer type) {
//        return null;
//    }
//
    private String sortSign(Map<String, ? extends Object> nvps, String priKey) {
        nvps.remove("sign");
        Object[] key = nvps.keySet().toArray();
        Arrays.sort(key);
        StringBuilder buf = new StringBuilder();
        String signatureStr = "";
        for (int i = 0; i < key.length; i++) {
            if (StringUtils.isNotEmpty(nvps.get(key[i]).toString())) {
                buf.append(key[i]).append("=").append(nvps.get(key[i]).toString()).append("&");
            }
        }
        signatureStr = buf.append("key=").append(priKey).toString();
        return StringUtils.lowerCase(DigestUtils.sha512Hex(signatureStr));
    }
}
