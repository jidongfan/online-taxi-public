package com.fjd.servicedriveruser.controller;

import com.fjd.internalcommon.constant.DriverCarConstants;
import com.fjd.internalcommon.dto.DriverUser;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.response.DriverUserExistsResponse;
import com.fjd.servicedriveruser.service.DriverUserService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/13 22:37
 * @desc:
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private DriverUserService driverUserService;

    /**
     * 新增司机
     * @param driverUser
     * @return
     */
    @PostMapping("/user")
    public ResponseResult addUser(@RequestBody DriverUser driverUser){

        log.info(JSONObject.fromObject(driverUser).toString());
        ResponseResult responseResult = driverUserService.addUser(driverUser);
        return responseResult;
    }

    /**
     * 更新司机
     * @param driverUser
     * @return
     */
    @PutMapping("/user")
    public ResponseResult updateUser(@RequestBody DriverUser driverUser){
        log.info(JSONObject.fromObject(driverUser).toString());
        return driverUserService.updateDriverUser(driverUser);
    }

    /**
     * 根据条件查询司机
     * 如果想要根据多条件查询司机信息，这里接口写 /user
     * @param driverPhone
     * @return
     */
    @GetMapping("/check-driver/{driverPhone}")
    public ResponseResult<DriverUserExistsResponse> getUser(@PathVariable("driverPhone") String driverPhone){
        ResponseResult<DriverUser> driverUserByPhone = driverUserService.getDriverUserByPhone(driverPhone);
        DriverUser driverUserDb = driverUserByPhone.getData();
        Integer ifExists = DriverCarConstants.DRIVER_EXISTS;
        DriverUserExistsResponse response = new DriverUserExistsResponse();

        if(driverUserDb == null){
            ifExists = DriverCarConstants.DRIVER_NOT_EXITS;
            response.setDriverPhone(driverPhone);
        }else{
            response.setDriverPhone(driverUserDb.getDriverPhone());
        }

        //为了返回的类型如下类型，所以封装一个DriverUserExistsResponse对象返回{
        //    "code": 24,
        //    "message": "id Lorem dolore",
        //    "data": {
        //        "driverPhone": "18647683806",
        //        "isExists": 3
        //    }
        //}
        response.setIfExists(ifExists);

        return ResponseResult.success(response);
    }
}
