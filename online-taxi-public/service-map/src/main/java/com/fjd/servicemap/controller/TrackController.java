package com.fjd.servicemap.controller;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.response.TrackResponse;
import com.fjd.servicemap.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/19 10:57
 * @desc:
 */
@RestController
@RequestMapping("/track")
public class TrackController {

    @Autowired
    private TrackService trackService;

    /**
     * 添加轨迹
     * @param tid
     * @return
     */
    @RequestMapping("/add")
    public ResponseResult<TrackResponse> add(String tid){

        return trackService.add(tid);
    }
}
