package com.fjd.apidriver.remote;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/4/4 21:42
 * @desc:
 */
@FeignClient("service-order")
public interface ServiceOrderClient {

    /**
     * 去接乘客
     * @param orderRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/orderInfo/to-pick-up-passenger")
    public ResponseResult toPickUpPassenger(@RequestBody OrderRequest orderRequest);

    /**
     * 司机到达乘客上车点
     * @param orderRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/orderInfo/arrived-departure")
    public ResponseResult arrivedDeparture(@RequestBody OrderRequest orderRequest);

    /**
     * 司机接到乘客
     * @param orderRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/orderInfo/pick-up-passenger")
    public ResponseResult pickUpPassenger(OrderRequest orderRequest);
}
