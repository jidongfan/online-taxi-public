package com.fjd.internalcommon.dto;

import lombok.Data;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/29 23:50
 * @desc:
 */
@Data
public class OrderDriverResponse {

    private Long driverId;

    private String driverPhone;

    private Long carId;

    /**
     * 机动车驾驶证号
     */
    private String licenseId;

    /**
     * 车牌号
     */
    private String vehicleNo;

    /**
     * 车辆类型
     */
    private String vehicleType;
}
