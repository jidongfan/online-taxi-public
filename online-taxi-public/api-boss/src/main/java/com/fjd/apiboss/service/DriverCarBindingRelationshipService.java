package com.fjd.apiboss.service;

import com.fjd.apiboss.remote.ServiceDriverUserClient;
import com.fjd.internalcommon.dto.DriverCarBindingRelationship;
import com.fjd.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/15 23:48
 * @desc:
 */
@Service
public class DriverCarBindingRelationshipService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    /**
     * 司机与车辆绑定
     * @param driverCarBindingRelationship
     * @return
     */
    public ResponseResult bind(DriverCarBindingRelationship driverCarBindingRelationship){
       return serviceDriverUserClient.bind(driverCarBindingRelationship);
    }

    /**
     * 司机与车辆解绑
     * @param driverCarBindingRelationship
     * @return
     */
    public ResponseResult unbind(DriverCarBindingRelationship driverCarBindingRelationship){
        return serviceDriverUserClient.unbind(driverCarBindingRelationship);
    }


}
