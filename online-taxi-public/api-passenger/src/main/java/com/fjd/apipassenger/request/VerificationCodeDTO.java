package com.fjd.apipassenger.request;

/**
 * @author fanjidong
 * @version 1.0
 * @date 2022/8/30 15:12
 */
public class VerificationCodeDTO {

    private String passengerPhone;

    public String getPassengerPhone(){
        return passengerPhone;
    }

    public void setPassengerPhone(String passengerPhone){
        this.passengerPhone = passengerPhone;
    }
}
