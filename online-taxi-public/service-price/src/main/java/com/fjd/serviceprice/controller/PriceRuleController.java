package com.fjd.serviceprice.controller;

import com.fjd.internalcommon.dto.PriceRule;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.PriceRuleIsNewRequest;
import com.fjd.serviceprice.service.PriceRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

/**
 *
 * @author fanjidong
 * @since 2023-03-23
 */
@RestController
@RequestMapping("/price-rule")
public class PriceRuleController {

    @Autowired
    PriceRuleService priceRuleService;

    /**
     * 添加一条计价规则数据
     * @param priceRule
     * @return
     */
    @PostMapping("/add")
    public ResponseResult add(@RequestBody PriceRule priceRule){

        return priceRuleService.add(priceRule);
    }

    /**
     * 编辑修改计价规则
     * @param priceRule
     * @return
     */
    @PostMapping("/edit")
    public ResponseResult edit(@RequestBody PriceRule priceRule){

        return priceRuleService.edit(priceRule);
    }

    /**
     * 查询最新的计价规则
     * @param fareType
     * @return
     */
    @GetMapping("/get-newest-version")
    public ResponseResult<PriceRule> getNewestVersion(@RequestParam String fareType){

        return priceRuleService.getNewestVersion(fareType);
    }

    /**
     * 判断传入的版本号是否是最新的版本
     * @param priceRuleIsNewRequest
     * @return
     */
    @PostMapping("/is-new")
    public ResponseResult<Boolean> isNew(@RequestBody PriceRuleIsNewRequest priceRuleIsNewRequest){
        return priceRuleService.isNew(priceRuleIsNewRequest.getFareType(), priceRuleIsNewRequest.getFareVersion());
    }

    /**
     * 根据城市和车型判断计价规则是否存在
     * @param priceRule
     * @return
     */
    @PostMapping("/if-exists")
    public ResponseResult<Boolean> ifExists(@RequestBody PriceRule priceRule){
        return priceRuleService.ifExists(priceRule);
    }

}
