package com.fjd.internalcommon.response;

import lombok.Data;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/19 8:51
 * @desc:
 */
@Data
public class TerminalResponse {

    private String tid;

    private Long carId;

    private String longitude; //经度

    private String latitude; // 纬度

}
