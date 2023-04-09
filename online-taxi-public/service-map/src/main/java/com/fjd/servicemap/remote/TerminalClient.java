package com.fjd.servicemap.remote;

import com.fjd.internalcommon.constant.AmapConfigConstants;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.response.TerminalResponse;
import com.fjd.internalcommon.response.TrsearchResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/19 8:40
 * @desc:
 */
@Service
public class TerminalClient {

    @Value("${map.key}")
    private String amapKey;

    @Value("${map.sid}")
    private String amapsid;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取terminal id  tid
     * @param name
     * @param desc
     * @return
     */
    public ResponseResult<TerminalResponse> add(String name, String desc){
        //拼装请求的url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TERMINAL_ADD);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&");
        url.append("sid=" + amapsid);
        url.append("&");
        url.append("name=" + name);
        url.append("&");
        url.append("desc=" + desc);

        //获取值
        System.out.println("创建终端请求：" + url.toString());
        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        System.out.println("创建终端响应：" + forEntity.getBody());
        /**
         * 解析json
         *{
         *     "data": {
         *         "name": "车辆1",
         *         "tid": 651227363,
         *         "sid": 901235
         *     },
         *     "errcode": 10000,
         *     "errdetail": null,
         *     "errmsg": "OK"
         * }
         */
        String body = forEntity.getBody();
        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");
        String tid = data.getString("tid");
        //封装data，返回
        TerminalResponse terminalResponse = new TerminalResponse();
        terminalResponse.setTid(tid);

        return ResponseResult.success(terminalResponse);
    }

    /**
     * 周边搜索
     * @param center
     * @param radius
     * @return
     */
    public  ResponseResult<List<TerminalResponse>> aroundsearch(String center, Integer radius){
        //拼装请求的url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TERMINAL_AROUNDSEARCH);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&");
        url.append("sid=" + amapsid);
        url.append("&");
        url.append("center=" + center);
        url.append("&");
        url.append("radius=" + radius);

        //获取值
        System.out.println("终端搜索请求：" + url.toString());
        ResponseEntity<String> forEntity = restTemplate.postForEntity(url.toString(), null, String.class);
        System.out.println("终端搜索响应：" + forEntity.getBody());

        /**
         * {
         * 	"data": {
         * 		"results": [{
         * 			"createtime": 1679269473618,
         * 			"desc": "1637600979625549826",
         * 			"locatetime": 1679223906440,
         * 			"location": {
         * 				"accuracy": 550.0,
         * 				"direction": 511.0,
         * 				"distance": 0,
         * 				"height": null,
         * 				"latitude": 40.00305,
         * 				"longitude": 116.32842,
         * 				"speed": 255.0,
         * 				"trackProps": null
         *                        },
         * 			"name": "京N2222",
         * 			"props": null,
         * 			"tid": 652715132* 		}]
         * 	}
         * }
         **/
        //解析终端搜索结果
        String body = forEntity.getBody();
        JSONObject result = JSONObject.fromObject(body);
        JSONObject data = result.getJSONObject("data");

        ArrayList<TerminalResponse> terminalResponseList = new ArrayList<>();

        JSONArray results = data.getJSONArray("results");
        for (int i = 0; i < results.size(); i++) {
            TerminalResponse terminalResponse = new TerminalResponse();

            JSONObject jsonObject = results.getJSONObject(i);
            Long carId = jsonObject.getLong("desc");
            String tid = jsonObject.getString("tid");

            JSONObject location = jsonObject.getJSONObject("location");
            String longitude = location.getString("longitude");
            String latitude = location.getString("latitude");

            terminalResponse.setCarId(carId);
            terminalResponse.setTid(tid);
            terminalResponse.setLongitude(longitude);
            terminalResponse.setLatitude(latitude);

            terminalResponseList.add(terminalResponse);
        }

        return ResponseResult.success(terminalResponseList);

    }

    /**
     * 轨迹搜索
     * @param tid
     * @param startTime
     * @param endTime
     * @return
     */
    public ResponseResult<TrsearchResponse> trsearch(String tid, Long startTime, Long endTime){
        //拼装请求的url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.TERMINAL_TRSEARCH);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&");
        url.append("sid=" + amapsid);
        url.append("&");
        url.append("tid=" + tid);
        url.append("&");
        url.append("starttime=" + startTime);
        url.append("&");
        url.append("endtime=" + endTime);

        /*{
            "data": {
            "counts": 1,
                    "tracks": [
            {
                "counts": 9,
                 "degradedParams": {},
                "distance": 49,
                "time": 58007,
                "trid": 240
            }
        ]
        }}
      */

        System.out.println("高德地图查询轨迹结果请求：" + url.toString());
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url.toString(), String.class);
        System.out.println("高德地图查询轨迹结果响应：" + forEntity.getBody());

        JSONObject result = JSONObject.fromObject(forEntity.getBody());
        JSONObject data = result.getJSONObject("data");
        int counts = data.getInt("counts");
        if (counts == 0){
            return null;
        }
        JSONArray tracks = data.getJSONArray("tracks");
        long driverMile = 0L;
        long driveTime = 0L;
        for (int i = 0; i < tracks.size(); i++) {
            JSONObject jsonObject = tracks.getJSONObject(i);

            long distance = jsonObject.getLong("distance");
            driverMile = driverMile + distance;

            long time = jsonObject.getLong("time");
            time = time / (1000 * 60);
            driveTime = driveTime + time;
        }

        TrsearchResponse trsearchResponse = new TrsearchResponse();
        trsearchResponse.setDriveMile(driverMile);
        trsearchResponse.setDriveTime(driveTime);
        return ResponseResult.success(trsearchResponse);
    }
}
