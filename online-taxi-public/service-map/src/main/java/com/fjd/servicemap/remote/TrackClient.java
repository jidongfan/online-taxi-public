package com.fjd.servicemap.remote;

import com.alibaba.cloud.commons.lang.StringUtils;
import com.fjd.internalcommon.constant.AmapConfigConstants;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.response.TrackResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/19 11:01
 * @desc:
 */
@Service
@Slf4j
public class TrackClient {
    @Value("${map.key}")
    private String amapKey;

    @Value("${map.sid}")
    private String amapsid;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseResult<TrackResponse> add(String tid){
        //拼装请求的url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TRACK_ADD);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&");
        url.append("sid=" + amapsid);
        url.append("&");
        url.append("tid=" + tid);

        log.info("高德地图创建轨迹请求：" + url);
        //获取值
        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        /**
         * 解析json
         {
         "data": {
         "trname": "测试轨迹",
         "trid": 60
         },
         "errcode": 10000,
         "errdetail": null,
         "errmsg": "OK"
         }
         */
        String body = forEntity.getBody();
        log.info("高德地图创建轨迹响应：" + body);
        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");
        //轨迹id
        String trid = data.getString("trid");
        //轨迹名称
        String trname = null;
        if(data.has("trname")){
            trname = data.getString("trname");
        }
        //封装data，返回
        TrackResponse trackResponse = new TrackResponse();
        trackResponse.setTrid(trid);
        trackResponse.setTrname(trname);

        return ResponseResult.success(trackResponse);
    }
}
