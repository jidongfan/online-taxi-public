package com.fjd.internalcommon.request;

import lombok.Data;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/9 7:57
 * @desc:
 */
@Data
public class ForecastPriceDTO {

    private String depLongitude; //出发地经度

    private String depLatitude; //出发地纬度

    private String destLongitude; //目的地经度

    private String destLatitude; //目的地纬度

    private String cityCode; //城市编码

    private String vehicleType; //车辆类型
}
