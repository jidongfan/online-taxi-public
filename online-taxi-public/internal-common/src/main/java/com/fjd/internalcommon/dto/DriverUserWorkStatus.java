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
 * @since 2023-03-17
 */
@Data
public class DriverUserWorkStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id; //主键id

    /**
     * 不用主键，只是记录一个司机工作状态而已
     */
    private Long driverId; //司机id

    private Integer workStatus; //司机工作状态

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

}
