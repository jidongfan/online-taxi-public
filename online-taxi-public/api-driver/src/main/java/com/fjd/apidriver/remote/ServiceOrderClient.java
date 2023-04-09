package com.fjd.apidriver.remote;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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
    public ResponseResult pickUpPassenger(@RequestBody OrderRequest orderRequest);

    /**
     * 乘客到达目的地，行程终止
     * @param orderRequest
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/orderInfo/passenger-getoff")
    public ResponseResult passengerGetoff(@RequestBody OrderRequest orderRequest);

    /**
     * 司机取消订单
     * @param orderId
     * @param identity
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/orderInfo/cancel")
    public ResponseResult cancel(@RequestParam Long orderId, @RequestParam String identity);

    /**
     * 司机发起收款，修改订单状态
     * @param orderRequest
     * @return
     */
    @PostMapping("/orderInfo/push-pay-info")
    public ResponseResult pushPayInfo(@RequestBody OrderRequest orderRequest);
}
