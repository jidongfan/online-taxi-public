package com.fjd.servicedriveruser.controller;

import com.fjd.internalcommon.dto.DriverUserWorkStatus;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.servicedriveruser.service.DriverUserWorkStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fanjidong
 * @since 2023-03-17
 */
@RestController
public class DriverUserWorkStatusController {

    @Autowired
    DriverUserWorkStatusService driverUserWorkStatusService;

    @PostMapping("/driver-user-work-status")
    public ResponseResult changeWorkStatus(@RequestBody DriverUserWorkStatus driverUserWorkStatus){
        return driverUserWorkStatusService.changeWorkStatus(driverUserWorkStatus.getDriverId(), driverUserWorkStatus.getWorkStatus());
    }

}
