package com.ruoyi.web.controller.business;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.business.domain.TPaymentRequest;
import com.ruoyi.business.service.ITPaymentRequestService;
import com.ruoyi.business.service.ITWhiteIpService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.enums.OrderEnum;
import com.ruoyi.common.utils.PaymentUtil;
import com.ruoyi.common.utils.RedisLock;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.ip.IpUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.payment.IPaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/api")
@Slf4j
public class ApiController extends BaseController {

    @Autowired
    private ITWhiteIpService ipService;

    @Autowired
    private RedisLock redisLock;

    @Autowired
    private ITPaymentRequestService paymentRequestService;





//    /**
//     * 代付回调
//     * @param request
//     * @param response
//     * @param serviceKey
//     */
//    @RequestMapping("/withdrawal/notify/{serviceKey}")
//    public void withdrawCallback(HttpServletRequest request, HttpServletResponse response, @PathVariable String serviceKey) throws IOException {
//        Map<String, Object> parameterMap = getParameterMap(request);
//        log.info("代付回调参数：{}，serviceKey：{}", parameterMap, serviceKey);
//        //查看IP白名单
//        String requestIp = IpUtils.getIpAddr(request);
//        TWhiteIp whiteIp = ipService.getByCode(serviceKey);
//        boolean checkIp = ipService.checkIp(serviceKey,requestIp);
//        if (!checkIp) {
//            log.info("{}回调不在白名单内:{}",requestIp,parameterMap);
//            response.getWriter().print("ip is not allowed");
//            return;
//        }
//        IPaymentService paymentService = SpringUtils.getBean(serviceKey);
//        String billNo = paymentService.getOrderNo(parameterMap);
//        TWithdrawRequest withdrawRequest = withdrawRequestService.getById(billNo);
//        if (withdrawRequest == null) {
//            log.info("{}订单号不存在:{}",billNo,parameterMap);
//            response.getWriter().print("orderNo is not exist");
//            return;
//        }
//        String key =  "withdrawlRequest:approve:"+billNo;
//        try {
//            WithdrawalCallbackResult result = paymentService.withdrawalCallback(parameterMap);
//            BigDecimal realAmount = result.getRealAmount();
//            BigDecimal orderAmount = withdrawRequest.getWithdrawAmount();
//            String orderNo = result.getOrderNo();
//            String upOrderNo = result.getUpOrderNo();
//            if (redisLock.tryLock(key, 3, 10, TimeUnit.SECONDS)) {
//                if (result.getStatus() == 2) {
//                    //订单回调成功，执行回调
//                    if(realAmount == null || orderAmount.compareTo(realAmount) == 0){
//                        if(withdrawRequestService.doSuccess(orderNo,upOrderNo,result.getPayImg(),null)){
//                            //异步通知回调下游成功
//                            withdrawRequest.setStatus(OrderEnum.SUCCESS.getCode());
//                            AsyncManager.me().execute(AsyncFactory.callback(billNo));
//                            log.info("代付订单成功通知：{}，serviceKey：{}", parameterMap, serviceKey);
//                            response.getWriter().print(paymentService.getSuccessMsg());
//                        } else {
//                            log.warn("代付回调成功，内部处理失败：{},serviceKey:{}",parameterMap,serviceKey);
//                            response.getWriter().print("success");
//                        }
//                    } else {
//                        response.getWriter().print("代付回调的金额和订单金额不一致");
//                        log.info("代付回调的金额和订单金额不一致：{} serviceKey：{}",parameterMap,serviceKey);
//                    }
//                } else if(result.getStatus() == 3){
//
//                    //2025-01-14 上游回调不直接通知下游 ，改成码商抢单大厅那里处理、
//                    String alias = whiteIp.getAlias();
//                    String note = StringUtils.isEmpty(result.getErrMsg()) ? "代付失败" : result.getErrMsg();
//                    if(withdrawRequestService.doBack(orderNo,"["+alias+"]" + note)){
//                        //withdrawRequest.setStatus(OrderEnum.FAIL.getCode());
//                        //AsyncManager.me().execute(AsyncFactory.callback(billNo));
//                        log.info("代付订单回滚抢单池：{}，serviceKey：{}", parameterMap, serviceKey);
//                        response.getWriter().print(result.getSuccessMsg());
//                    } else {
//                        log.warn("代付回调失败，内部处理失败：{},serviceKey:{}",parameterMap,serviceKey);
//                    }
//                }else {
//                    log.info("代付回调失败：{}，serviceKey：{}", parameterMap, serviceKey);
//                    response.getWriter().print("fail");
//                }
//            }
//        } catch (Exception e) {
//            log.error("三方回调错误,serviceKey:{},parameterMap:{},message:{}", serviceKey, parameterMap, e.getMessage(), e);
//        } finally {
//            redisLock.unlock(key);
//        }
//    }


    /**
     * 代收回调
     * @param request
     * @param response
     * @param serviceKey
     */
    @RequestMapping("/deposit/notify/{serviceKey}")
    public void depositCallback(HttpServletRequest request, HttpServletResponse response, @PathVariable String serviceKey) {
        Map<String, Object> parameterMap = getParameterMap(request);
        String key = "depositRequestCallback:"+serviceKey;
        String requestIp = IpUtils.getIpAddr(request);
        log.info("收到{}的代付回调,参数:{}",requestIp,parameterMap);
        //查看IP白名单
        boolean checkIp = ipService.checkIp(serviceKey,requestIp);
        if (!checkIp) {
            log.info("{}回调不在白名单内:{}",requestIp,parameterMap);
            return;
        }
        try {
            if(redisLock.tryLock(key, 3, 10, TimeUnit.SECONDS)){
                log.info("获取锁成功,开始回调处理:{}",parameterMap);
                IPaymentService paymentService = SpringUtils.getBean(serviceKey);
                String billNo = paymentService.getOrderNo(parameterMap);
                TPaymentRequest paymentRequest = paymentRequestService.selectTPaymentRequestByRequestId(billNo);
                if (paymentRequest == null || !paymentRequest.getStatus().equals(OrderEnum.PENDDING.getCode())) {
                    log.info("{}订单回调，订单不存在或已处理过了",billNo);
                    return;
                }
                boolean result = paymentService.depositCallback(parameterMap);
                if (result) {
                    BigDecimal realAmount = paymentService.getRealAmount(parameterMap);
                    if(paymentRequestService.doSuccess(billNo,realAmount)){
                        // 预付提醒
                        //AsyncManager.me().execute(AsyncFactory.checkPrepAmount(paymentRequest.getMchId()));
                        //订单成功提醒
                        //AsyncManager.me().execute(AsyncFactory.depositSuccessNotice(billNo));
                        //回调下游
                        //AsyncManager.me().execute(AsyncFactory.paymentCallback(billNo));
                        log.info("代收回调成功：{}，serviceKey：{}", parameterMap, serviceKey);
                        response.getWriter().print(paymentService.getSuccessMsg());
                    }
                }else {
                    log.info("代收回调失败{}",parameterMap);
                    response.getWriter().print("fail");
                }
            }
        } catch (Exception e) {
            log.error("三方回调错误,serviceKey:{},parameterMap:{},message:{}", serviceKey, parameterMap, e.getMessage(), e);
        } finally {
            redisLock.unlock(key);
        }
    }

    /**
     * 测试回调
     * @param request
     * @param response
     */
    @RequestMapping("/test")
    public void test(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> parameterMap = getParameterMap(request);
        try {
//            TWithdrawRequest withdrawRequest =    withdrawRequestService.getById("MAUL997704967139704833");
//            webSocketService.send2CodeUser("test02",new WebSocketMessage(WebsocketMessageEnum.WITHDRAW_ORDER_TIMEOUT.getCode(),withdrawRequest));
//              webSocketService.newOrderReceived(withdrawRequest);
//            response.getWriter().print("ok");
//            paymentRequestService.dayReport();
//            paymentRequestService.channelDayReport();

            //customerDsService.checkPrepAmount(1033L);
//            log.error("开始**************");
//            Map<String,Object> map = new HashMap();
//            map.put("code",500);
//            map.put("error","通道已关闭！");
//            customerDsService.sendErrorToBot(new ErrorMsgToBotBean("奈斯","A801",JSONObject.toJSONString(map)));
//            log.error("结束**************");
            log.info("回调：{}",parameterMap);
            response.getWriter().print("success");
        } catch (Exception e) {
            log.error("测试异常{}",e.getMessage(),e);
        }
    }

    public Map<String, Object> getParameterMap(HttpServletRequest request) {
        //header map
        // 参数Map
        Map<?, ?> properties = request.getParameterMap();
        if (properties.size()== 0) {
            //尝试json获取
            try {
                BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(), "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();
                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);
                JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
                if(StringUtils.isNotEmpty(request.getHeader("X-Imx-Sign"))){
                    ((Map<String, Object>) jsonObject).put("X-Imx-Sign",request.getHeader("X-Imx-Sign"));
                    ((Map<String, Object>) jsonObject).put("json_params",responseStrBuilder.toString());
                }
                return jsonObject;
            } catch (IOException e) {
                log.info("解析错误：{}",e.getMessage(),e);
            }
        }
        // 返回值Map
        Map<String, Object> returnMap = new HashMap<String, Object>();
        Iterator<?> entries = properties.entrySet().iterator();

        Map.Entry<String, Object> entry;
        String name = "";
        String value = "";
        Object valueObj = null;
        while (entries.hasNext()) {
            entry = (Map.Entry<String, Object>) entries.next();
            name = (String) entry.getKey();
            valueObj = entry.getValue();
            if (null == valueObj) {
                value = "";
            } else if (valueObj instanceof String[]) {
                String[] values = (String[]) valueObj;
                for (int i = 0; i < values.length; i++) {
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length() - 1);
            } else {
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
    }

    /**
     * 获取签名
     * @param map
     * @return
     */
    private String getSign(Map<String,Object> map,String priKey) {
        //计算sign
        return PaymentUtil.sortSign(map, priKey);
    }



//    public static void main(String[] args) throws UnsupportedEncodingException {
//        String s = "1-YBLA01928302借记卡已销户";
//        String after = URLEncoder.encode(s,"UTF-8");
//        System.out.println(after);
//        System.out.println(URLDecoder.decode(after,"UTF-8"));
//        System.out.println(new String(s.getBytes("ISO-8859-1"),"UTF-8"));
//        BigDecimal amount = new BigDecimal(10);
//        amount = amount.setScale(2);
//        System.out.println(amount.toString());
//    }

}
