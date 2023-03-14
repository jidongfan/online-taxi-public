package com.fjd.apidriver.service;

import com.fjd.apidriver.remote.ServiceDriverUserClient;
import com.fjd.internalcommon.dto.DriverUser;
import com.fjd.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/15 0:24
 * @desc:
 */
@Service
public class UserService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    /**
     * 更新司机信息
     * @param driverUser
     * @return
     */
    public ResponseResult updateUser(DriverUser driverUser){
        return serviceDriverUserClient.updateUser(driverUser);
    }
}
