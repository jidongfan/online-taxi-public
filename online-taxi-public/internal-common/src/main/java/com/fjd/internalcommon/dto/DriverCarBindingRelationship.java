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
 * @since 2023-03-15
 */
@Data
public class DriverCarBindingRelationship implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long id;

    /**
     * driver_user表中司机的id
     */
    private Long driverId;

    /**
     * car表中car的id
     */
    private Long carId;

    /**
     * 绑定状态 1：绑定 2：解绑
     */
    private Integer bindState;

    /**
     * 司机与车的绑定时间
     */
    private LocalDateTime bindingDate;

    /**
     * 司机与车的解绑时间
     */
    private LocalDateTime unBindingDate;

}
