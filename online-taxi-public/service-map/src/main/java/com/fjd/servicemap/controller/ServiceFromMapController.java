package com.fjd.servicemap.controller;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.servicemap.service.ServiceFromMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/19 7:54
 * @desc: 服务管理控制器
 */
@RestController
@RequestMapping("/service")
public class ServiceFromMapController {

    @Autowired
    private ServiceFromMapService serviceFromMapService;

    /**
     * 创建服务
     * @param name
     * @return
     */
    @RequestMapping("/add")
    public ResponseResult add(String name){

        return serviceFromMapService.add(name);
    }
}
