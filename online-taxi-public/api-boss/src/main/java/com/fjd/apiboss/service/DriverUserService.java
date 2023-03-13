package com.fjd.apiboss.service;

import com.fjd.apiboss.remote.ServiceDriverUserClient;
import com.fjd.internalcommon.dto.DriverUser;
import com.fjd.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/14 7:33
 * @desc:
 */
@Service
public class DriverUserService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    public ResponseResult addDriverUser(DriverUser driverUser){

        return serviceDriverUserClient.addDriverUser(driverUser);
    }
}
