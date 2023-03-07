package com.fjd.internalcommon.response;

import lombok.Data;

/**
 * @author fanjidong
 * @version 1.0
 * @date 2022/9/2 10:42
 */
@Data
public class TokenResponse {
    private String assessToken;
    private String refreshToken;
}
