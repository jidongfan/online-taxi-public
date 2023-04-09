package com.fjd.apidriver.controller;

import com.fjd.apidriver.service.ApiDriverOrderInfoService;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/4/4 21:39
 * @desc:
 */
@RestController
@RequestMapping("/orderInfo")
public class ApiDriverOrderInfoController {

    @Autowired
    private ApiDriverOrderInfoService apiDriverOrderInfoService;

    /**
     * 去接乘客
     * @param orderRequest
     * @return
     */
    @PostMapping("/to-pick-up-passenger")
    public ResponseResult toPickUpPassenger(@RequestBody OrderRequest orderRequest){

        return apiDriverOrderInfoService.toPickUpPassenger(orderRequest);
    }

    /**
     * 司机到达乘客上车点
     * @param orderRequest
     * @return
     */
    @PostMapping("/arrived-departure")
    public ResponseResult arrivedDeparture(@RequestBody OrderRequest orderRequest){

        return apiDriverOrderInfoService.arrivedDeparture(orderRequest);
    }

    /**
     * 司机接到乘客
     * @param orderRequest
     * @return
     */
    @PostMapping("/pick-up-passenger")
    public ResponseResult pickUpPassenger(@RequestBody OrderRequest orderRequest){
        return apiDriverOrderInfoService.pickUpPassenger(orderRequest);
    }

    /**
     * 乘客到达目的地，行程终止
     * @param orderRequest
     * @return
     */
    @PostMapping("/passenger-getoff")
    public ResponseResult passengerGetoff(@RequestBody OrderRequest orderRequest){
        return apiDriverOrderInfoService.passengerGetoff(orderRequest);
    }

    /**
     * 司机取消订单
     * @param orderId
     * @return
     */
    @PostMapping("/cancel")
    public ResponseResult cancel(@RequestParam Long orderId){
        return apiDriverOrderInfoService.cancel(orderId);
    }
}
