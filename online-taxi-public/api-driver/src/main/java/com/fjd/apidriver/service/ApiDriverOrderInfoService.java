package com.fjd.apidriver.service;

import com.fjd.apidriver.remote.ServiceOrderClient;
import com.fjd.internalcommon.constant.IdentityConstants;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.xml.ws.Response;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/4/4 21:41
 * @desc:
 */
@Service
public class ApiDriverOrderInfoService {

    @Autowired
    private ServiceOrderClient serviceOrderClient;

    /**
     * 去接乘客
     * @param orderRequest
     * @return
     */
    public ResponseResult toPickUpPassenger(@RequestBody OrderRequest orderRequest){
        return serviceOrderClient.toPickUpPassenger(orderRequest);
    }

    /**
     * 司机到达乘客上车点
     * @param orderRequest
     * @return
     */
    public ResponseResult arrivedDeparture(OrderRequest orderRequest){
        return serviceOrderClient.arrivedDeparture(orderRequest);
    }

    /**
     * 司机接到乘客
     * @param orderRequest
     * @return
     */
    public ResponseResult pickUpPassenger(OrderRequest orderRequest){
        return serviceOrderClient.pickUpPassenger(orderRequest);
    }

    /**
     * 乘客到达目的地，行程终止
     * @param orderRequest
     * @return
     */
    public ResponseResult passengerGetoff(OrderRequest orderRequest){
        return serviceOrderClient.passengerGetoff(orderRequest);
    }

    /**
     * 司机取消订单
     * @param orderId
     * @return
     */
    public ResponseResult cancel(Long orderId){
        return serviceOrderClient.cancel(orderId, IdentityConstants.DRIVER_IDENTITY);
    }
}
