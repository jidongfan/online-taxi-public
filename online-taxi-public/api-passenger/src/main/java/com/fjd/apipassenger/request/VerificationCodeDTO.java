package com.fjd.apipassenger.request;

import lombok.Data;

/**
 * @author fanjidong
 * @version 1.0
 * @date 2022/8/30 15:12
 */
@Data
public class VerificationCodeDTO {

    private String passengerPhone;
    private String verificationCode;

}
