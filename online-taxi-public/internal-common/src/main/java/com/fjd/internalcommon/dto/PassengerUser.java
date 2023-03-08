package com.fjd.internalcommon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/5 17:34
 * @desc:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerUser {

    private Long id;

    private LocalDateTime gmtCreate;

    private LocalDateTime gmtModified;

    private String passengerPhone;

    private String passengerName;

    private byte passengerGender;

    private byte state;

    private String profilePhoto;
}
