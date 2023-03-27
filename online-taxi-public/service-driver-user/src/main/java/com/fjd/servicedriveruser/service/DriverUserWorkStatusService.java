package com.fjd.servicedriveruser.service;

import com.fjd.internalcommon.dto.DriverUserWorkStatus;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.servicedriveruser.mapper.DriverUserWorkStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fanjidong
 * @since 2023-03-17
 */
@Service
public class DriverUserWorkStatusService {

    @Autowired
    DriverUserWorkStatusMapper driverUserWorkStatusMapper;

    public ResponseResult changeWorkStatus(Long driverId, Integer workStatus){

        HashMap<String, Object> queryMap = new HashMap<>();
        queryMap.put("driver_id", driverId);
        List<DriverUserWorkStatus> driverUserWorkStatuses = driverUserWorkStatusMapper.selectByMap(queryMap);

        DriverUserWorkStatus driverUserWorkStatus = driverUserWorkStatuses.get(0);
        driverUserWorkStatus.setWorkStatus(workStatus);
        driverUserWorkStatusMapper.updateById(driverUserWorkStatus);

        return ResponseResult.success("");
    }

}
