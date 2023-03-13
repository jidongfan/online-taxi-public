package com.fjd.internalcommon.dto;

import lombok.Data;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/12 18:49
 * @desc:
 */
@Data
public class DriverUser {

    private Integer id;
    private String address;
    private String driverName;
    private String driverPhone;
    private Integer driverGender;
    private Data driverBirthday;
    private String driverNation;
    private String driverContactAddress;
    private String licenseId;
    private Data getDriverLicenseDate;
    private Data driverLicenseOn;
    private Data driverLicenseOff;
    private Integer taxiDriver;
    private String certificateNo;
    private String networkCarIssueOrganization;
    private Data networkCarIssueDate;
    private Data getNetworkCarProofDate;
    private Data networkCarProofOn;
    private Data networkCarProofOff;
    private Data registerDate;
    private Integer commercialType;
    private String contractCompany;
    private Data contractOn;
    private Data contractOff;
    private Integer state;
    private Data gmtCreate;
    private Data gmtModified;

}
