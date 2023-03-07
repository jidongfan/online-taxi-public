package com.fjd.apipassenger.controller;

import com.fjd.apipassenger.service.TokenService;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.dto.TokenResult;
import com.fjd.internalcommon.response.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/7 22:37
 * @desc:
 */

@RestController
public class TokenController {

    @Autowired
    private TokenService tokenService;

    /**
     * 双token刷新接口
     * 用刷新token去更新token值
     * @param tokenResponse
     * @return
     */
    @PostMapping("/token-refresh")
    public ResponseResult refreshToken(@RequestBody TokenResponse tokenResponse){

        String refreshTokenSrc = tokenResponse.getRefreshToken();
        System.out.println("原来的 refreshToken，" + refreshTokenSrc);

        return tokenService.refreshToken(refreshTokenSrc);
    }

}
