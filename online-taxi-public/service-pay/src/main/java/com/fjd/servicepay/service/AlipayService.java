package com.fjd.servicepay.service;

import com.fjd.internalcommon.request.OrderRequest;
import com.fjd.servicepay.remote.ServiceOrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/4/7 11:56
 * @desc:
 */
@Service
public class AlipayService {

    @Autowired
    private ServiceOrderClient serviceOrderClient;

    /**
     * 支付宝回调信息
     * @param orderId
     */
    public void pay(Long orderId){
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderId(orderId);
        serviceOrderClient.pay(orderRequest);
    }
}
