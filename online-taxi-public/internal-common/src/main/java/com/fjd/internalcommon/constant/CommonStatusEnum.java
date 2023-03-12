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
     * 验证码错误提示：1000-1099
     * 1000代表验证码已过期，1099代表验证码不正确
     */
    VERIFICATION_CODE_ERROR(1099,"验证码不正确"),

    /**
     * Token类提示：1100 - 1199
     */
    TOKEN_ERROR(1199, "token错误"),

    /**
     * 用户提示：1200 - 1299
     */
    USER_NOT_EXIST(1200, "当前用户不存在"),

    /**
     * 计价规则不存在：1300 - 1399
     */
    PRICE_RULE_EMPTY(1300, "计价规则不存在"),

    /**
     * 请求地图信息：1400 - 1499
     */
    MAP_DISTRICT_ERROR(1400, "请求地图错误"),

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
