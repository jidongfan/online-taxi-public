package com.fjd.servicemap.remote;

import com.fjd.internalcommon.constant.AmapConfigConstants;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.response.ServiceResponse;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/19 7:57
 * @desc:
 */
@Service
public class ServiceClient {

    @Value("${map.key}")
    private String amapKey;

    @Autowired
    private RestTemplate restTemplate;


    /**
     * 添加服务
     * @param name
     * @return
     */
    public ResponseResult add(String name){

        //拼装请求的url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.SERVICE_ADD_URL);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&");
        url.append("name=" + name);

        //获取值
        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        /**
         * 解析json
         * {
         *     "data": {
         *         "name": "飞滴出行",
         *         "sid": 903457
         *     },
         *     "errcode": 10000,
         *     "errdetail": null,
         *     "errmsg": "OK"
         * }
         */
        String body = forEntity.getBody();
        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");
        //封装data，返回
        ServiceResponse serviceResponse = new ServiceResponse();
        String sid = data.getString("sid");
        serviceResponse.setSid(sid);

        return ResponseResult.success(serviceResponse);
    }
}
