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

}
