package com.fjd.servicemap.remote;

import com.fjd.internalcommon.constant.AmapConfigConstants;
import com.fjd.internalcommon.dto.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/12 11:41
 * @desc:
 */
@Service
public class MapDicDistrictClient {

    @Value("${map.key}")
    private String amapKey;

    @Autowired
    RestTemplate restTemplate;

    public String dicDistrict(String keywords){

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

        //获取值
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url.toString(), String.class);


        return forEntity.getBody();
    }

}
