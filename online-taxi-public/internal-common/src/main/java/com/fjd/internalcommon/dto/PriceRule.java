package com.fjd.internalcommon.dto;

import lombok.Data;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/11 16:41
 * @desc:
 */
@Data
public class PriceRule {

    private String cityCode; //城市代码

    private String vehicleType;  //车辆类型

    private Double startFare;  //起步价

    private Integer startMile;  //起步里程

    private Double unitPricePerMile; //一公里多少钱

    private Double unitPricePerMinute;  //每分钟多少钱
}
