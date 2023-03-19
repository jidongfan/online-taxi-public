package com.fjd.internalcommon.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author fanjidong
 * @since 2023-03-15
 */
@Data
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 公司标识id
     */
    private Long id;

    /**
     * 车辆所在城市（注册地行政区别代码）
     */
    private String address;

    /**
     * 车辆牌照
     */
    private String vehicle;

    /**
     * 车牌颜色 1.蓝 2.黄 3.黑 4.白 5.绿色 9.其他
     */
    private String plateColor;

    /**
     * 限定载客位
     */
    private Integer seats;

    /**
     * 车辆厂牌
     */
    private String brand;

    /**
     * 车辆类型
     */
    private String vehicleType;

    /**
     * 车辆所有人
     */
    private String ownerName;

    /**
     * 车身颜色 1.白 2.黑	
     */
    private String vehicleColor;

    /**
     * 发动机号
     */
    private String engineId;

    /**
     * 车辆VIN码
     */
    private String vin;

    /**
     * 车辆注册日期
     */
    private LocalDate certifyDateA;

    /**
     * 车辆燃料类型 1.汽油 2.柴油 3.天然气 4.液化气 5.电动 9.其他
     */
    private String fueType;

    /**
     * 发动机排量 单位：毫升
     */
    private String engineDisplace;

    /**
     * 车辆运输证发证机构
     */
    private String transAgency;

    /**
     * 车辆经营区域
     */
    private String transArea;

    /**
     * 车辆运输证有效期起
     */
    private LocalDate transDateStart;

    /**
     * 车辆运输证有效期止
     */
    private LocalDate transDateEnd;

    /**
     * 车辆初次登记日期
     */
    private LocalDate certifyDateB;

    /**
     * 车辆检修状态（0：为检修 1：检修 2：未知）
     */
    private String fixState;

    /**
     * 车辆下次年检时间
     */
    private LocalDate nextFixDate;

    /**
     * 车辆年度审验状态
     */
    private String checkState;

    /**
     * 发票打印设备序列号
     */
    private String feePrintId;

    /**
     * 卫星定位装置品牌
     */
    private String gpsBrand;

    /**
     * 卫星定位装置型号
     */
    private String gpsModel;

    /**
     * 卫星定位设备安装日期
     */
    private LocalDate gpsInstallDate;

    /**
     * 车辆报备日期
     */
    private LocalDate registerDate;

    /**
     * 服务类型（1.网络预约出租车 2.巡游出租车 3.私人小客车合乘）
     */
    private Integer commercialType;

    /**
     * 运价类型
     */
    private String fareType;

    /**
     * 状态：0有效 1无效
     */
    private Boolean states;

    /**
     * 车辆对应tid 终端id
     */
    private String tid;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 修改时间
     */
    private LocalDateTime gmtModified;

}
