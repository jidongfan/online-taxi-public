package com.fjd.servicepay.controller;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.fjd.servicepay.service.AlipayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/4/6 22:18
 * @desc:
 */
@RequestMapping("/alipay")
@RestController
@ResponseBody
public class AlipayController {

    @Autowired
    private AlipayService alipayService;

    /**
     * 支付宝付款
     * @param subject  付款类型
     * @param outTradeNo  轨迹编号
     * @param totalAmount  总付款金额
     * @return
     */
    @GetMapping("/pay")
    public String pay(String subject, String outTradeNo, String totalAmount){
        AlipayTradePagePayResponse response;
        try {
            response = Factory.Payment.Page().pay(subject, outTradeNo, totalAmount, "");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return response.getBody();
    }

    @PostMapping("/notify")
    public String notify(HttpServletRequest request) throws Exception {
        System.out.println("支付宝回调 notify");
        String tradeStatus = request.getParameter("trade_status");

        if(tradeStatus.trim().equals("TRADE_SUCCESS")){
            Map<String, String> param = new HashMap<>();

            Map<String, String[]> parameterMap = request.getParameterMap();
            for (String name : parameterMap.keySet()) {
                param.put(name, request.getParameter(name));
            }

            if(Factory.Payment.Common().verifyNotify(param)){
                System.out.println("通过支付宝的验证");
                String outTradeNo = param.get("out_trade_no"); //交易号 -- 用来存放我们的订单号
                Long orderId = Long.parseLong(outTradeNo);

                alipayService.pay(orderId);

            }else{
                System.out.println("支付宝验证，不通过！");
            }
        }
        return "success";
    }



}
