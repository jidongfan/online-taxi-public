package com.fjd.internalcommon.constant;

import lombok.Data;
import lombok.Getter;

/**
 * @author fanjidong
 * @version 1.0
 * @date 2022/8/31 19:01
 */
public enum  CommonStatusEnum {
    /**
     * 成功
     * */
    SUCCESS(1,"success"),
    /**
     * 失败
     */
    FAIL(0,"fail")
    ;
    @Getter
    private int code;
    @Getter
    private String value;
    CommonStatusEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }
}
