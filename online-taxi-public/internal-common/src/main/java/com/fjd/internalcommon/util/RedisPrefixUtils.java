package com.fjd.internalcommon.util;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/7 7:57
 * @desc:
 */
public class RedisPrefixUtils {

    //验证码的前缀
    public static String verificationCodePerfix = "passenger-verification-code-";

    //token 存储前缀
    public static String tokenPrefix = "token-";

    /**
     * 根据手机号生成key
     * @param passengerPhone 手机号
     * @return
     */
    public static String generatorKeyByPhone(String passengerPhone){
        return verificationCodePerfix + passengerPhone;
    }


    /**
     * 根据手机号和身份标识，生成token
     * @param phone
     * @param identity
     * @return
     */
    public static String generatorTokenKey(String phone, String identity){
        return tokenPrefix + phone + "-" + identity;
    }
}
