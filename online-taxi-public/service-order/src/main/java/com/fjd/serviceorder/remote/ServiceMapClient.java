package com.fjd.serviceorder.remote;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.response.TerminalResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/27 16:22
 * @desc:
 */
@FeignClient("service-map")
public interface ServiceMapClient {

    /**
     * 搜索周边车辆
     * @param center
     * @param radius
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/terminal/aroundsearch")
    public ResponseResult<List<TerminalResponse>>  terminalAroundSearch(@RequestParam String center, @RequestParam Integer radius);
}
