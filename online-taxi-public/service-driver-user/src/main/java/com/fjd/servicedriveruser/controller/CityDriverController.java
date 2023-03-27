package com.fjd.servicedriveruser.controller;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.servicedriveruser.mapper.DriverUserMapper;
import com.fjd.servicedriveruser.service.CityDriverUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/27 10:51
 * @desc:
 */
@RestController
@RequestMapping("/city-driver")
public class CityDriverController {

    @Autowired
    private DriverUserMapper driverUserMapper;

    @Autowired
    private CityDriverUserService cityDriverUserService;

    /**
     * 根据cityCode查询当前城市是否有可用司机
     * @param cityCode
     * @return
     */
    @GetMapping("/is-available-driver")
    public ResponseResult<Boolean> isAvailableDriver(String cityCode){
        return cityDriverUserService.isAvailableDriver(cityCode);
    }

}
