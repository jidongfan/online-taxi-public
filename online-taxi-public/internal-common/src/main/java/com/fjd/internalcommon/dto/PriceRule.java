package com.fjd.internalcommon.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author fanjidong
 * @since 2023-03-23
 */
@Data
public class PriceRule implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 城市代码
     */
    private String cityCode;

    /**
     * 车辆类型型
     */
    private String vehicleType;

    /**
     * 起步价
     */
    private Double startFare;

    /**
     * 起步里程
     */
    private Integer startMile;

    /**
     * 一公里多少前
     */
    private Double unitPricePerMile;

    /**
     * 每分钟多少钱
     */
    private Double unitPricePerMinute;

    /**
     * 运价版本，修改一次版本变一次，防止版本变更价格不准确
     */
    private Integer fareVersion;

    /**
     * 运价类型编码
     */
    private String fareType;
}
