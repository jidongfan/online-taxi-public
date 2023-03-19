package com.fjd.servicemap.service;

import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.response.TerminalResponse;
import com.fjd.servicemap.remote.TerminalClient;
import com.google.common.net.InetAddresses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
