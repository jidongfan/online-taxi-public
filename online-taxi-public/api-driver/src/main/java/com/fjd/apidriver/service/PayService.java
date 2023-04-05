package com.fjd.apidriver.service;

import com.fjd.internalcommon.dto.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/4/5 21:03
 * @desc:
 */
@Service
public class PayService {

    public ResponseResult pushPayInfo(Long orderId, Double price, Long passengerId){
        //封装消息

        //推送消息

        return ResponseResult.success();
    }
}
