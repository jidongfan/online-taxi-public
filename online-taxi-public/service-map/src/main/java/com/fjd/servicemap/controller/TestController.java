package com.fjd.servicemap.controller;

import com.fjd.internalcommon.dto.DicDistrict;
import com.fjd.servicemap.mapper.DicDistrictMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/9 21:25
 * @desc:
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(){
        return "service-map";
    }

    @Autowired
    DicDistrictMapper mapper;

    @GetMapping("/test-map")
    public String testMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("address_code", "110000");
        List<DicDistrict> dicDistricts = mapper.selectByMap(map);
        System.out.println(dicDistricts);

        return "test-map";
    }
}
