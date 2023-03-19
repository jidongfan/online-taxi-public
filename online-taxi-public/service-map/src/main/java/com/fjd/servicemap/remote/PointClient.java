package com.fjd.servicemap.remote;

import com.fjd.internalcommon.constant.AmapConfigConstants;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.PointDTO;
import com.fjd.internalcommon.request.PointRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;


/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/19 15:29
 * @desc:
 */
@Service
public class PointClient {

    @Value("${map.key}")
    private String amapKey;

    @Value("${map.sid}")
    private String amapsid;

    @Autowired
    private RestTemplate restTemplate;


    /**
     * 轨迹上传
     * @param pointRequest
     * @return
     */
    public ResponseResult upload(PointRequest pointRequest){
        //拼装请求的url
        StringBuilder url = new StringBuilder();
        url.append(AmapConfigConstants.POINT_UPLOAD);
        url.append("?");
        url.append("key=" + amapKey);
        url.append("&");
        url.append("sid=" + amapsid);
        url.append("&");
        url.append("tid=" + pointRequest.getTid());
        url.append("&");
        url.append("trid=" + pointRequest.getTrid());
        url.append("&");
        url.append("points=");
        PointDTO[] points = pointRequest.getPoints();
        //循环拼接数组
        url.append("%5B"); //%5B -> [
        for (PointDTO p : points) {

            url.append("%7B"); //%7B -> {
            String location = p.getLocation();
            String locatetime = p.getLocatetime();
            url.append("%22location%22"); //%22 -> "
            url.append("%3A");  //%3A -> :
            url.append("%22" + location + "%22");
            url.append("%2C"); //, -> %2C
            url.append("%22locatetime%22");
            url.append("%3A"); // %3A -> :
            url.append("%22" + locatetime + "%22");
            url.append("%7D"); //%7D -> }
        }
        url.append("%5D"); // 55D -> ]

        //获取值
        System.out.println("高德地图请求：" + url.toString());
        ResponseEntity<String> forEntity = restTemplate.postForEntity(URI.create(url.toString()), null, String.class);
        System.out.println("高德地图响应：" + forEntity.getBody());

        return ResponseResult.success();
    }
}
