package com.fjd.servicedriveruser.service;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.servicedriveruser.mapper.DriverUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/27 10:47
 * @desc:
 */
@Service
public class CityDriverUserService {

    @Autowired
    private DriverUserMapper driverUserMapper;

    /**
     * 根据cityCode查询当前城市是否有可用司机
     * @param cityCode
     * @return
     */
    public ResponseResult<Boolean> isAvailableDriver(String cityCode){

        int i = driverUserMapper.selectDriverUserCountByCityCode(cityCode);
        if(i > 0){
            return ResponseResult.success(true);
        }else {
            return ResponseResult.success(false);
        }
    }
}
