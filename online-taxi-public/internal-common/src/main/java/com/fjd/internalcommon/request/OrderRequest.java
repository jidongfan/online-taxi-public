package com.fjd.internalcommon.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/21 22:30
 * @desc:
 */
@Data
public class OrderRequest {

    /**
     * 订单id
     */
    private Long orderId;

    private Long passengerId; //乘客id
    private String passengerPhone; //乘客手机号
    private String address; //下单行政区域
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departTime; //出发时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderTime; //下单时间
    private String departure; //出发地址
    private String depLongitude; //出发经度
    private String depLatitude; //出发纬度
    private String destination; //目的地地址
    private String destLongitude; //目的地经度
    private String destLatitude; //目的地纬度
    private String encrypt; //坐标加密标识：1.gcj-02 2.wjs-04 3.bd-09 4.cgcs-2000北斗 4.0-其他
    private String fareType; //运价类型编码
    private Integer fareVersion; //运价版本，修改一次版本变一次，防止版本变更价格不准确

    //请求设备唯一码
    private String deviceCode;

    /**
     * 司机去接乘客出发时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime toPickUpPassengerTime;

    /**
     * 司机去接乘客时，司机的经度
     */
    private String toPickUpPassengerLongitude;

    /**
     * 司机去接乘客时，司机的纬度
     */
    private String toPickUpPassengerLatitude;

    /**
     * 司机去接乘客时，司机位置
     */
    private String toPickUpPassengerAddress;

    /**
     * 司机到达上车点的时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime driverArrivedDepartureTime;

    /**
     * 接到乘客的时间
     */
    private LocalDateTime pickUpPassengerTime;

    /**
     * 接到乘客的经度
     */
    private String pickUpPassengerLongitude;

    /**
     * 接到乘客的纬度
     */
    private String pickUpPassengerLatitude;

    /**
     * 乘客下车时间
     */
    private LocalDateTime passengerGetoffTime;

    /**
     * 乘客下车经度
     */
    private String passengerGetoffLongitude;

    /**
     * 乘客下车纬度
     */
    private String passengerGetoffLatitude;


}
