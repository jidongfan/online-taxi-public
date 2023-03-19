package com.fjd.apidriver.service;

import com.fjd.apidriver.remote.ServiceDriverUserClient;
import com.fjd.apidriver.remote.ServviceVerificationcodeClient;
import com.fjd.internalcommon.constant.CommonStatusEnum;
import com.fjd.internalcommon.constant.DriverCarConstants;
import com.fjd.internalcommon.constant.IdentityConstants;
import com.fjd.internalcommon.constant.TokenConstants;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.VerificationCodeDTO;
import com.fjd.internalcommon.response.DriverUserExistsResponse;
import com.fjd.internalcommon.response.NumberCodeResponse;
import com.fjd.internalcommon.response.TokenResponse;
import com.fjd.internalcommon.util.JwtUtils;
import com.fjd.internalcommon.util.RedisPrefixUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.Response;
import java.util.concurrent.TimeUnit;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/16 0:39
 * @desc:
 */
@Service
@Slf4j
public class VerificationDriverService {

    @Autowired
    private ServiceDriverUserClient serviceDriverUserClient;

    @Autowired
    private ServviceVerificationcodeClient servviceVerificationcodeClient;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public ResponseResult checkAndSendVerificationCode(String driverPhone){

        //查询 service-driver-user服务中 ，该手机号的司机存不存在
        ResponseResult<DriverUserExistsResponse> driverUserExistsResponseResponseResult = serviceDriverUserClient.checkDriver(driverPhone);
        DriverUserExistsResponse data = driverUserExistsResponseResponseResult.getData();
        Integer ifExists = data.getIfExists();
        if(DriverCarConstants.DRIVER_NOT_EXITS.equals(ifExists)){
            return ResponseResult.fail(CommonStatusEnum.DRIVER_NOT_EXISTS.getCode(), CommonStatusEnum.DRIVER_NOT_EXISTS.getValue());

        }

        //获取验证码
        ResponseResult<NumberCodeResponse> numberCodeResult = servviceVerificationcodeClient.getVerificationCode(6);
        NumberCodeResponse numberCodeResponse = numberCodeResult.getData();
        int numberCode = numberCodeResponse.getNumberCode();
        log.info("验证码："+numberCode);

        //调用第三方，发送验证码 第三方：阿里短信服务，腾讯，华信，容联

        //存入redis  两步：1.生成key 2.存入value
        String key = RedisPrefixUtils.generatorKeyByPhone(driverPhone, IdentityConstants.DRIVER_IDENTITY);
        stringRedisTemplate.opsForValue().set(key,numberCode+"", 2, TimeUnit.MINUTES);

        return ResponseResult.success("");
    }

    /**
     * 校验验证码
     * @param driverPhone 手机号
     * @param identity 身份标识
     * @param verificationCode 验证码
     * @return
     */
    public ResponseResult checkCode(String driverPhone, String identity, String verificationCode){
        //根据手机号，去redis读取验证码
        //生成key
        String key = RedisPrefixUtils.generatorKeyByPhone(driverPhone, identity);

        //根据key获取value
        String codeRedis = stringRedisTemplate.opsForValue().get(key);

        //校验验证码
        if(StringUtils.isBlank(codeRedis)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if(!verificationCode.trim().equals(codeRedis.trim())){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }

        //颁发令牌，不应该用魔法值，用常量
        String accessToken = JwtUtils.generatorToken(driverPhone, IdentityConstants.DRIVER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        String refreshToken = JwtUtils.generatorToken(driverPhone, IdentityConstants.DRIVER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);

        //将token存到redis中
        String accessTokenKey = RedisPrefixUtils.generatorTokenKey(driverPhone, IdentityConstants.DRIVER_IDENTITY, TokenConstants.ACCESS_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(accessTokenKey, accessToken, 30, TimeUnit.DAYS);

        //设置refreshToken比accessToken晚过期
        String refreshTokenKey = RedisPrefixUtils.generatorTokenKey(driverPhone, IdentityConstants.DRIVER_IDENTITY, TokenConstants.REFRESH_TOKEN_TYPE);
        stringRedisTemplate.opsForValue().set(refreshTokenKey, refreshToken, 31, TimeUnit.DAYS);

        //响应
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setAccessToken(accessToken);
        tokenResponse.setRefreshToken(refreshToken);

        return ResponseResult.success(tokenResponse);
    }
}
