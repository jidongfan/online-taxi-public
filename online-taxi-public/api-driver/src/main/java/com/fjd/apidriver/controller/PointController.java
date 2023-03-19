package com.fjd.apidriver.controller;

import com.fjd.apidriver.service.PointService;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.ApiDriverPointRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/19 17:50
 * @desc:
 */
@RestController
@RequestMapping("/point")
public class PointController {

    @Autowired
    private PointService pointService;


    /**
     * 通过carId获取 tid 和 trid，通过获取位置信息，上传轨迹
     * @param apiDriverPointRequest
     * @return
     */
    @PostMapping("/upload")
    public ResponseResult upload(@RequestBody ApiDriverPointRequest apiDriverPointRequest){

        return  pointService.upload(apiDriverPointRequest);

    }
}
