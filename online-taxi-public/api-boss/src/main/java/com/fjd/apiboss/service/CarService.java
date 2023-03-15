package com.fjd.apiboss.service;

import com.fjd.apiboss.remote.ServiceDriverUserClient;
import com.fjd.internalcommon.dto.Car;
import com.fjd.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/15 23:31
 * @desc:
 */
@Service
public class CarService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult addCar(Car car){
        return serviceDriverUserClient.addCar(car);
    }
}
