package com.fjd.servicedriveruser.controller;

import com.fjd.internalcommon.dto.Car;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.servicedriveruser.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author fanjidong
 * @since 2023-03-15
 */
@RestController
public class CarController {

    @Autowired
    CarService carService;

    /**
     * 增加car
     * @param car
     * @return
     */
    @PostMapping("/car")
    public ResponseResult addCar(@RequestBody Car car){

        return carService.addCar(car);
    }

    /**
     * 查询car
     * @param carId
     * @return
     */
    @GetMapping("/car")
    public ResponseResult<Car> getCarById(Long carId){

        return carService.getCarById(carId);
    }

}
