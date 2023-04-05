package com.fjd.serviceorder.remote;

import com.fjd.internalcommon.dto.PriceRule;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.PriceRuleIsNewRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/26 11:49
 * @desc:
 */
@FeignClient("service-price")
public interface ServicePriceClient {

    /**
     * 判断当前费用类型的运价版本是否是最新的
     * @param priceRuleIsNewRequest
     * @return
     */
    @PostMapping("/price-rule/is-new")
    public ResponseResult<Boolean> isNew(@RequestBody PriceRuleIsNewRequest priceRuleIsNewRequest);


    @RequestMapping(method = RequestMethod.GET, value = "/price-rule/if-exists")
    public ResponseResult<Boolean> ifPriceExists(@RequestBody PriceRule priceRule);

    @RequestMapping(method = RequestMethod.POST,  value = ("/calculate-price"))
    public ResponseResult<Double> calculatePrice(@RequestParam Integer distance, @RequestParam Integer duration, @RequestParam String cityCode, @RequestParam String vehicleType);
}
