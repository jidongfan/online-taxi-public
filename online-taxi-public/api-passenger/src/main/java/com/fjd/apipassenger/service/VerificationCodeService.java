package com.fjd.apipassenger.service;
import com.fjd.apipassenger.remote.ServicePassengerUserClient;
import com.fjd.apipassenger.remote.ServiceVerificationcodeClient;
import com.fjd.internalcommon.constant.CommonStatusEnum;
import com.fjd.internalcommon.constant.IdentityConstant;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.request.VerificationCodeDTO;
import com.fjd.internalcommon.response.NumberCodeResponse;
import com.fjd.internalcommon.response.TokenResponse;
import com.fjd.internalcommon.util.JwtUtils;
import jdk.management.resource.ResourceType;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author fanjidong
 * @version 1.0
 * @date 2022/8/30 15:36
 */
@Service
public class VerificationCodeService {

    @Autowired
    private ServiceVerificationcodeClient serviceVerificationcodeClient;

    //验证码的前缀
    private String verificationCodePerfix = "passenger-verification-code-";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ServicePassengerUserClient servicePassengerUserClient;

    /**
     * 生成验证码
     * @param passengerPhone 手机号
     * @return
     */
    public ResponseResult generatorCode(String passengerPhone){
        //调用验证码服务，获取验证码
        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationcodeClient.getNumberCode(6);
        int numberCode = numberCodeResponse.getData().getNumberCode();
        System.out.println(numberCode);

        //存入redis
        //key,value,过期时间
        String key = generatorKeyByPhone(passengerPhone);
        //存入redis，设置两分钟后过期
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);

        //通过短信服务商，将对应的验证码发送到手机上，阿里短信服务、腾讯短信通、华信、容联
       // return ResponseResult.success("");

        return ResponseResult.success();

    }

    /**
     * 根据手机号生成key
     * @param passengerPhone 手机号
     * @return
     */
    private String generatorKeyByPhone(String passengerPhone){
        return verificationCodePerfix + passengerPhone;
    }

    /**
     * 校验验证码
     * @param passengerPhone 手机号
     * @param verificationCode 验证码
     * @return
     */
    public ResponseResult checkCode(String passengerPhone, String verificationCode){
        //根据手机号，去redis读取验证码
        //生成key
        String key = generatorKeyByPhone(passengerPhone);

        //根据key获取value
        String codeRedis = stringRedisTemplate.opsForValue().get(key);

        //校验验证码
        if(StringUtils.isBlank(codeRedis)){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }
        if(!verificationCode.trim().equals(codeRedis.trim())){
            return ResponseResult.fail(CommonStatusEnum.VERIFICATION_CODE_ERROR.getCode(), CommonStatusEnum.VERIFICATION_CODE_ERROR.getValue());
        }

        //判断原来是否有用户，并进行对应的处理
        VerificationCodeDTO verificationCodeDTO = new VerificationCodeDTO();
        verificationCodeDTO.setPassengerPhone(passengerPhone);
        servicePassengerUserClient.loginOrRegister(verificationCodeDTO);

        //颁发令牌，不应该用魔法值，用常量
        String token = JwtUtils.generatorToken(passengerPhone, IdentityConstant.PASSENGER_IDENTITY);

        //响应
        TokenResponse tokenResponse = new TokenResponse();
        tokenResponse.setToken(token);

        return ResponseResult.success(tokenResponse);
    }
}
