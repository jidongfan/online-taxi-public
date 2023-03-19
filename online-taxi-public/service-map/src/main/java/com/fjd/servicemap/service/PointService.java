package com.fjd.servicemap.service;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.PointRequest;
import com.fjd.servicemap.remote.PointClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/19 15:46
 * @desc:
 */
@Service
public class PointService {

    @Autowired
    private PointClient pointClient;

    /**
     * 上传轨迹
     * @param pointRequest
     * @return
     */
    public ResponseResult upload(PointRequest pointRequest){

        return pointClient.upload(pointRequest);

    }
}
