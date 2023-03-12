package com.fjd.servicemap.service;

import com.fjd.internalcommon.constant.AmapConfigConstants;
import com.fjd.internalcommon.dto.ResponseResult;
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

    @Value("${map.key}")
    private String amapKey;

    public ResponseResult initDicDistrict(String keywords){

        //https://restapi.amap.com/v3/config/district?keywords=北京&subdistrict=2&key=<用户的key>
        //subdistrict=2&key=<用户的key>
        //拼装请求的url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.DISTRICT_URL);
        url.append("?");
        url.append("keywords=" + keywords);
        url.append("&");
        url.append("subdistrict=3");
        url.append("&");
        url.append("key=" + amapKey);

        //解析结果

        //插入数据库



        return ResponseResult.success();
    }
}
