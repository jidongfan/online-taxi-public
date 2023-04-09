package com.fjd.apidriver.service;

import com.fjd.apidriver.remote.ServiceDriverUserClient;
import com.fjd.internalcommon.dto.DriverUser;
import com.fjd.internalcommon.dto.DriverUserWorkStatus;
import com.fjd.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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

    /**
     * 修改司机工作状态
     * @param driverUserWorkStatus
     * @return
     */
    public ResponseResult changeWorkStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus){
        return serviceDriverUserClient.changeWorkStatus(driverUserWorkStatus);
    }

    /**
     * 根据driverPhone查询司机信息
     * @param driverPhone
     * @return
     */
    public ResponseResult getDriverCarBindingRelationship(String driverPhone){
        //根据driverPhone查询司机信息
        return serviceDriverUserClient.getDriverCarRelationShip(driverPhone);
    }
}
