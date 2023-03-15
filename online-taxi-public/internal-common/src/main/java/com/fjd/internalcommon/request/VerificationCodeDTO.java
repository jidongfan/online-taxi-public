package com.fjd.internalcommon.request;

import lombok.Data;

/**
 * @author fanjidong
 * @version 1.0
 * @date 2022/9/2 14:32
 */
@Data
public class VerificationCodeDTO {

    /**
     * 乘客手机号
     */
    private String passengerPhone;
    /**
     * 乘客验证码
     */
    private String verificationCode;

    /**
     * 司机手机号
     */
    private String driverPhone;

}
