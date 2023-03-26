package com.fjd.internalcommon.response;

import lombok.Data;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/9 8:16
 * @desc:
 */
@Data
public class ForecastPriceResponse {

    private Double price; //价格

    private String cityCode; //城市编码

    private String vehicleType; //车辆类型

    private String fareType; //费用类型

    private Integer fareVersion; //运价版本，修改一次版本变一次，防止版本变更价格不准确
}
