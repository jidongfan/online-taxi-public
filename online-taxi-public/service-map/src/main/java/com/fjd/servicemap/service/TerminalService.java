package com.fjd.servicemap.service;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.response.TerminalResponse;
import com.fjd.internalcommon.response.TrsearchResponse;
import com.fjd.servicemap.remote.TerminalClient;
import com.google.common.net.InetAddresses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/19 8:40
 * @desc:
 */
@Service
public class TerminalService {

    @Autowired
    private TerminalClient terminalClient;

    /**
     * 创建终端
     * @param name
     * @return
     */
    public ResponseResult<TerminalResponse> add(String name, String desc){

        return terminalClient.add(name, desc);
    }

    /**
     * 周边搜索
     * @param center
     * @param radius
     * @return
     */
    public  ResponseResult<List<TerminalResponse>> aroudsearch(String center, Integer radius){

        return terminalClient.aroundsearch(center, radius);
    }

    /**
     * 轨迹搜索
     * @param tid
     * @param startTime
     * @param endTime
     * @return
     */
    public ResponseResult<TrsearchResponse> trsearch(String tid, Long startTime, Long endTime){

        return terminalClient.trsearch(tid, startTime, endTime);
    }
}
