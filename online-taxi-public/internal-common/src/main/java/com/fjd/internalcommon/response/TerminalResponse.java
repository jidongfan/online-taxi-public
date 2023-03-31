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

    private String carId;

    private Long longitude; //经度

    private Long latitude; // 纬度

}
