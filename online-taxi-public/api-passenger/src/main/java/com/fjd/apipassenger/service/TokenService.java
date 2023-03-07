package com.fjd.apipassenger.service;

import com.auth0.jwt.JWT;
import com.fjd.internalcommon.constant.CommonStatusEnum;
import com.fjd.internalcommon.constant.TokenConstants;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.dto.TokenResult;
import com.fjd.internalcommon.response.TokenResponse;
import com.fjd.internalcommon.util.JwtUtils;
import com.fjd.internalcommon.util.RedisPrefixUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/7 22:40
 * @desc:
 */
public class TokenService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public ResponseResult refreshToken(String refreshTokenSrc){

        //解析refreshToken
        TokenResult tokenResult = JwtUtils.checkToken(refreshTokenSrc);
        if(tokenResult == null){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(), CommonStatusEnum.TOKEN_ERROR.getValue());
        }
        String phone = tokenResult.getPhone();
        String identity = tokenResult.getIdentity();

        //读取redis中 的refreshToken
        String refreshTokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, TokenConstants.REFRESH_TOKEN_TYPE);
        String refreshTokenRedis = stringRedisTemplate.opsForValue().get(refreshTokenKey);

        //校验refreshToken
        if((StringUtils.isBlank(refreshTokenRedis)) || (!refreshTokenSrc.trim().equals(refreshTokenRedis.trim()))){
            return ResponseResult.fail(CommonStatusEnum.TOKEN_ERROR.getCode(), CommonStatusEnum.TOKEN_ERROR.getValue());
        }

        //生成双token
        String refreshToken = JwtUtils.generatorToken(phone, identity, TokenConstants.REFRESH_TOKEN_TYPE);
        String accessToken = JwtUtils.generatorToken(phone, identity, TokenConstants.ACCESS_TOKEN_TYPE);

        //生成accessTokenKey
        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(phone, identity, TokenConstants.ACCESS_TOKEN_TYPE);

        //设置key的过期时间
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);

        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setRefreshToken(refreshToken);
        tokenResponse.setAccessToken(accessToken);

        return ResponseResult.success(tokenResponse);
    }
}
