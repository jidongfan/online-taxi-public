package com.fjd.servicemap.remote;

import com.fjd.internalcommon.constant.AmapConfigConstants;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.response.DirectionResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/9 22:50
 * @desc:
 */
@Service
@Slf4j
public class MapDirectionClient {

    @Value("${map.key}")
    private String mapKey;

    @Autowired
    private RestTemplate restTemplate;

    public DirectionResponse direction(String depLongitude, String depLatitude, String destLongitude, String destLatitude){

        //组装请求url
        //https://restapi.amap.com/v3/direction/driving?origin=116.481028,39.989643&destination=116.465302,40.004717&extensions=all&output=json&key=b808311f7b2ebc7faa4d711e36d8ed76
        StringBuilder urlBuild = new StringBuilder();
        urlBuild.append(AmapConfigConstants.DIRECTION_URL);
        urlBuild.append("?");
        urlBuild.append("origin=" + depLongitude + "," + depLatitude);
        urlBuild.append("&");
        urlBuild.append("destination=" + destLongitude + "," + destLatitude);
        urlBuild.append("&");
        urlBuild.append("extensions=base");
        urlBuild.append("&");
        urlBuild.append("output=json");
        urlBuild.append("&");
        urlBuild.append("key=" + mapKey);
        log.info(urlBuild.toString());

        //调用高德的接口
        ResponseEntity<String> directionEntity = restTemplate.getForEntity(urlBuild.toString(), String.class);
        String directionString = directionEntity.getBody();
        log.info("高德地图路径规划返回信息：" + directionString);

        //解析接口
        DirectionResponse directionResponse = parseDirectionEntity(directionString);

        return directionResponse;
    }


    public DirectionResponse parseDirectionEntity(String directionString){

        DirectionResponse directionResponse = null;

        try {
            //解析json从最外层的{}开始
            JSONObject result = JSONObject.fromObject(directionString);
            //首先判断是否有status这个key
            if(result.has(AmapConfigConstants.STATUS)){
                 //0：请求失败；1：请求成功
                 int status = result.getInt(AmapConfigConstants.STATUS);
                 if(status == 1){
                     if(result.has(AmapConfigConstants.ROUTE)){
                         JSONObject routeObject = result.getJSONObject(AmapConfigConstants.ROUTE);
                         JSONArray pathsArray = routeObject.getJSONArray(AmapConfigConstants.PATHS);
                         //取第一种方案，可以通过strategy调整策略，速度最快 耗时最短 等等
                         JSONObject pathsObject = pathsArray.getJSONObject(0);
                         directionResponse = new DirectionResponse();
                         //获取距离和时长
                         if(pathsObject.has(AmapConfigConstants.DISTANCE)){
                             int distance = pathsObject.getInt(AmapConfigConstants.DISTANCE);
                             directionResponse.setDistance(distance);
                         }
                         if(pathsObject.has(AmapConfigConstants.DURATION)){
                             int duration = pathsObject.getInt(AmapConfigConstants.DURATION);
                             directionResponse.setDuration(duration);
                         }
                     }
                 }
            }

        }catch (Exception e){

        }

        return directionResponse;
    }

}
