package com.fjd.apidriver.service;

import com.fjd.apidriver.remote.ServiceOrderClient;
import com.fjd.apidriver.remote.ServiceSsePushClient;
import com.fjd.internalcommon.constant.IdentityConstants;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.OrderRequest;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/4/5 21:03
 * @desc:
 */
@Service
public class PayService {

    @Autowired
    private ServiceSsePushClient serviceSsePushClient;

    @Autowired
    private ServiceOrderClient serviceOrderClient;

    /**
     * 司机发起收款
     * @param orderId
     * @param price
     * @param passengerId
     * @return
     */
    public ResponseResult pushPayInfo(Long orderId, Double price, Long passengerId){
        //封装消息
        JSONObject message = new JSONObject();
        message.put("price", price);
        message.put("passengerId", passengerId);

        //修改订单状态
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setOrderId(orderId);

        serviceOrderClient.pushPayInfo(orderRequest);

        //推送消息
        serviceSsePushClient.pushPayInfo(passengerId, IdentityConstants.PASSENGER_IDENTITY, message.toString());

        return ResponseResult.success();
    }
}
