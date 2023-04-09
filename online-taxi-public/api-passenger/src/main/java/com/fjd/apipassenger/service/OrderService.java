package com.fjd.apipassenger.service;

import com.fjd.apipassenger.remote.ServiceOrderClient;
import com.fjd.internalcommon.constant.CommonStatusEnum;
import com.fjd.internalcommon.constant.IdentityConstants;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/4/9 10:00
 * @desc:
 */
@Service
public class OrderService {

    @Autowired
    private ServiceOrderClient serviceOrderClient;

    /**
     * 乘客下订单
     * @param orderRequest
     * @return
     */
    public ResponseResult add(OrderRequest orderRequest){
        return serviceOrderClient.add(orderRequest);
    }

    /**
     * 取消订单
     * @param orderId
     * @return
     */
    public ResponseResult cancel(Long orderId){
        return serviceOrderClient.cancel(orderId, IdentityConstants.PASSENGER_IDENTITY);
    }
}
