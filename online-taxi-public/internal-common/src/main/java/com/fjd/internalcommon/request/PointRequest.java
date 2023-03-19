package com.fjd.internalcommon.request;

import lombok.Data;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/19 15:26
 * @desc:
 */
@Data
public class PointRequest {

    private String tid;
    private String trid;
    private PointDTO[] points;
}
