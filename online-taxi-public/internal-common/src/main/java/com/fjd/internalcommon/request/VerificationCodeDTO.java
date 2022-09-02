package com.fjd.internalcommon.request;

import lombok.Data;

/**
 * @author fanjidong
 * @version 1.0
 * @date 2022/9/2 14:32
 */
@Data
public class VerificationCodeDTO {

    private String passengerPhone;
    private String verificationCode;

}
