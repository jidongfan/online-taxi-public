package com.fjd.servicemap.controller;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.servicemap.service.DicDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用来初始化 地区字典表
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/12 11:21
 * @desc:
 */
@RestController
public class DistrictController {

    @Autowired
    DicDistrictService dicDistrictService;

    @GetMapping("/dic-district")
    public ResponseResult initDicDistrict(String keywords){

        return dicDistrictService.initDicDistrict(keywords);
    }
}
