package com.fjd.internalcommon.response;

import lombok.Data;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/16 1:05
 * @desc:
 */
@Data
public class DriverUserExistsResponse {

    private String driverPhone;

    private Integer ifExists;
}
