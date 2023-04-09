package com.fjd.apipassenger.remote;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.OrderRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/31 0:18
 * @desc:
 */
@FeignClient("service-order")
public interface ServiceOrderClient {

    @RequestMapping(method = RequestMethod.POST, value = "/orderInfo/add")
    public ResponseResult add(@RequestBody OrderRequest orderRequest);

    /**
     * 测试调用多个服务 测试派单逻辑
     * @param orderId
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/test-real-time-order/{orderId}")
    public String dispatchRealTimeOrder(@PathVariable("orderId") Long orderId);

    /**
     * 取消订单
     * @param orderId
     * @param identity
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/orderInfo/cancel")
    public ResponseResult cancel(@RequestParam Long orderId, @RequestParam String identity);
}
