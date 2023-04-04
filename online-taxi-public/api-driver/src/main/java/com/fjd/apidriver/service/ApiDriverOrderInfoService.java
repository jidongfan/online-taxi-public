package com.fjd.apidriver.service;

import com.fjd.apidriver.remote.ServiceOrderClient;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.xml.ws.Response;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/4/4 21:41
 * @desc:
 */
@Service
public class ApiDriverOrderInfoService {

    @Autowired
    private ServiceOrderClient serviceOrderClient;

    public ResponseResult toPickUpPassenger(@RequestBody OrderRequest orderRequest){
        return serviceOrderClient.toPickUpPassenger(orderRequest);
    }
}
