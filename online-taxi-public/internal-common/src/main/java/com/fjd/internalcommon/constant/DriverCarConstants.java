package com.fjd.internalcommon.constant;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/15 20:56
 * @desc:
 */
public class DriverCarConstants {

    /**
     * 司机车辆状态：绑定1
     */
    public static Integer DRIVER_CAR_BIND = 1;

    /**
     * 司机车辆状态：解绑2
     */
    public static Integer DRIVER_CAR_UNBIND = 2;

    /**
     * 司机状态：0是有效
     */
    public static Integer DRIVER_STATE_VALID = 0;

    /**
     * 司机状态：1是无效
     */
    public static Integer DRIVER_STATE_INVALID = 1;

    /**
     * 司机状态：存在 1
     */
    public static Integer DRIVER_EXISTS = 1;

    /**
     * 司机状态：不存在 0
     */
    public static Integer DRIVER_NOT_EXITS = 0;

    /**
     * 司机工作状态：出车
     */
    public static Integer DRIVER_WORK_STATUS_START = 1;
    /**
     * 司机工作状态：收车
     */
    public static Integer DRIVER_WORK_STATUS_STOP = 0;
    /**
     * 司机工作状态：暂停
     */
    public static Integer DRIVER_WORK_STATUS_SUSPEND = 2;



}
