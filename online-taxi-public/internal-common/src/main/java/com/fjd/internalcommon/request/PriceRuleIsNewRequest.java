package com.fjd.internalcommon.request;

import lombok.Data;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/30 23:59
 * @desc:
 */
@Data
public class PriceRuleIsNewRequest {

    private String fareType;
    private Integer fareVersion;
}
