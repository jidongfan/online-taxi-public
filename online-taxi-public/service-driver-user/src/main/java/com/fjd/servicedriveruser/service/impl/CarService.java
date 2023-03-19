package com.fjd.servicedriveruser.service.impl;

import com.fjd.internalcommon.dto.Car;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.response.TerminalResponse;
import com.fjd.internalcommon.response.TrackResponse;
import com.fjd.servicedriveruser.mapper.CarMapper;
import com.fjd.servicedriveruser.remote.ServiceMapClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/15 7:42
 * @desc:
 */
@Service
public class CarService {

    @Autowired
    private CarMapper carMapper;

    @Autowired
    private ServiceMapClient serviceMapClient;

    /**
     * 增加car
     * @param car
     * @return
     */
    public ResponseResult addCar(Car car){
        LocalDateTime now = LocalDateTime.now();
        car.setGmtModified(now);
        car.setGmtCreate(now);

        //提前是为了生成车辆id
        carMapper.insert(car);

        // 获得此车辆 对应的 tid
        ResponseResult<TerminalResponse> responseResult = serviceMapClient.addTerminal(car.getVehicle(), car.getId() + "");
        String tid = responseResult.getData().getTid();
        car.setTid(tid);

        // 获得此车辆的轨迹id trid
        ResponseResult<TrackResponse> trackResponseResponseResult = serviceMapClient.addTrack(tid);
        String trid = trackResponseResponseResult.getData().getTrid();
        String trname = trackResponseResponseResult.getData().getTrname();
        car.setTrid(trid);
        car.setTrname(trname);

        carMapper.updateById(car);


        return ResponseResult.success("");
    }

    /**
     * 查询car
     * @param id
     * @return
     */
    public ResponseResult<Car> getCarById(Long id){

        HashMap<String, Object> map = new HashMap<>();
        map.put("id", id);

        List<Car> cars = carMapper.selectByMap(map);

        return ResponseResult.success(cars.get(0));

    }

}
