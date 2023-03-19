package com.fjd.servicedriveruser.remote;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.response.TerminalResponse;
import com.fjd.internalcommon.response.TrackResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.ws.Response;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/19 9:20
 * @desc:
 */
@FeignClient("service-map")
public interface ServiceMapClient {

    /**
     * 创建车辆的终端
     * @param name
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/terminal/add")
    public ResponseResult<TerminalResponse> addTerminal(@RequestParam String name, @RequestParam String desc);

    /**
     * 创建车辆轨迹
     * @param tid
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/track/add")
    public ResponseResult<TrackResponse> addTrack(@RequestParam String tid);
}
