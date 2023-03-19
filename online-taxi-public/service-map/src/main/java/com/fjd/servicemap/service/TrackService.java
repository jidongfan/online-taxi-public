package com.fjd.servicemap.service;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.response.TrackResponse;
import com.fjd.servicemap.remote.TrackClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/19 10:58
 * @desc:
 */
@Service
public class TrackService {

    @Autowired
    private TrackClient trackClient;

    /**
     * 添加车辆轨迹信息
     * @param tid
     * @return
     */
    public ResponseResult<TrackResponse> add(String tid){

        return trackClient.add(tid);
    }
}
