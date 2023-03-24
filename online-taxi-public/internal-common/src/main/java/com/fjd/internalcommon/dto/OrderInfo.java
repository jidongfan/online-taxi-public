package com.fjd.internalcommon.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author fanjidong
 * @since 2023-03-23
 */
@Data
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单id
     */
    private Long id;

    /**
     * 乘客id
     */
    private Long passengerId;

    /**
     * 乘客电话
     */
    private String passengerPhone;

    /**
     * 司机id
     */
    private Long driverId;

    /**
     * 司机电话
     */
    private String driverPhone;

    /**
     * 车辆id
     */
    private Long carId;

    /**
     * 出发地行政区码
     */
    private String address;

    /**
     * 订单发起时间
     */
    private LocalDateTime orderTime;

    /**
     * 预计用车时间
     */
    private LocalDateTime departTime;

    /**
     * 预计出发点
     */
    private String departure;

    /**
     * 出发点经度
     */
    private String depLongitude;

    /**
     * 出发点纬度
     */
    private String depLatitude;

    /**
     * 目的地
     */
    private String destination;

    /**
     * 目的地经度
     */
    private String destLongitude;

    /**
     * 目的地纬度
     */
    private String destLatitude;

    /**
     * 坐标加密标识  	1：GCJ-02 测绘局标准	2:  WGS64 GPS标准	3： BD-09百度标准	4：CGCS2000 北斗标准	0：其他
     */
    private Integer encrypt;

    /**
     * 运价类型编码
     */
    private String fareType;

    /**
     * 接单时车辆的经度
     */
    private String receiveOrderCarLongitude;

    /**
     * 接单时车辆的纬度
     */
    private String receiveOrderCarLatitude;

    /**
     * 接单时间或派单时间
     */
    private LocalDateTime receiveOrderTime;

    /**
     * 机动车驾驶证号
     */
    private String licenseId;

    /**
     * 车牌号
     */
    private String vehicleNo;

    /**
     * 司机去接乘客出发时间
     */
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

    /**
     * 撤销订单时间
     */
    private LocalDateTime cancelTime;

    /**
     * 取消的操作员：1.乘客 2.驾驶员 2.平台公司
     */
    private Integer cancelOperator;

    /**
     * 撤销代码类型 1.乘客提前撤销 2.司机提前撤销 3.平台公司撤销 4.乘客违约撤销 5.驾驶员违约撤销
     */
    private Integer cancelTypeCode;

    /**
     * 载客里程（米）
     */
    private Long driverMile;

    /**
     * 载客时间（分）
     */
    private Long driverTime;

    /**
     * 订单状态 1：订单开始 2：司机接单 3：去接乘客 4：司机到达乘客起点 5：乘客上车，司机开始行程 6：到达目的地，行程结束，未支付 7：发起收款 8：支付完成 9：订单取消
     */
    private Integer orderStatus;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

    /**
     * 运价版本，修改一次版本变一次，防止版本变更价格不准确
     */
    private Integer fareVersion;


}
