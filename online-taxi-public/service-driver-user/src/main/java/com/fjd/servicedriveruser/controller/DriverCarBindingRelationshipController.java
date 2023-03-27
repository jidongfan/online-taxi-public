package com.fjd.servicedriveruser.controller;

import com.fjd.internalcommon.dto.DriverCarBindingRelationship;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.servicedriveruser.service.DriverCarBindingRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fanjidong
 * @since 2023-03-15
 */
@RestController
@RequestMapping("/driverCarBindingRelationship")
public class DriverCarBindingRelationshipController {

    @Autowired
    DriverCarBindingRelationshipService driverCarBindingRelationshipService;

    @PostMapping("/bind")
    public ResponseResult bind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){

        return driverCarBindingRelationshipService.bind(driverCarBindingRelationship);
    }


    @PostMapping("/unbind")
    public ResponseResult unbind(@RequestBody DriverCarBindingRelationship driverCarBindingRelationship){

        return driverCarBindingRelationshipService.unbind(driverCarBindingRelationship);
    }
}
