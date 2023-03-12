package com.fjd.servicemap.service;

import com.fjd.internalcommon.constant.AmapConfigConstants;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.servicemap.remote.MapDicDistrictClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/12 11:23
 * @desc:
 */
@Service
public class DicDistrictService {

    @Autowired
    MapDicDistrictClient mapDicDistrictClient;

    public ResponseResult initDicDistrict(String keywords){

        //请求地图
        String dicDistrict = mapDicDistrictClient.dicDistrict(keywords);
        System.out.println(dicDistrict);

        //解析结果

        //插入数据库



        return ResponseResult.success();
    }
}
