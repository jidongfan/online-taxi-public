package com.fjd.internalcommon.util;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/4/3 10:16
 * @desc:
 */
public class SsePrefixUtils {

    /**
     * 分隔符
     */
    public static final String separator = "$";

    /**
     * 生成key
     * @param userId
     * @param identity
     * @return
     */
    public static String generatorSseKey(Long userId, String identity){
        return userId + separator + identity;
    }
}
