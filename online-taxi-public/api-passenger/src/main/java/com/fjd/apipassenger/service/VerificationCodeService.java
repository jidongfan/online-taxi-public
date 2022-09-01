package com.fjd.apipassenger.service;
import com.fjd.apipassenger.remote.ServiceVerificationcodeClient;
import com.fjd.internalcommon.dto.ResponseResult;
import com.fjd.internalcommon.response.NumberCodeResponse;
import net.sf.json.JSONObject;
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

    public ResponseResult generatorCode(String passengerPhone){
        //调用验证码服务，获取验证码
        System.out.println("调用验证码服务，获取验证码");

        ResponseResult<NumberCodeResponse> numberCodeResponse = serviceVerificationcodeClient.getNumberCode(7);
        int numberCode = numberCodeResponse.getData().getNumberCode();

        //key,value,过期时间
        String key = verificationCodePerfix + passengerPhone;
        //存入redis，设置两分钟后过期
        stringRedisTemplate.opsForValue().set(key,numberCode+"",2, TimeUnit.MINUTES);

        //通过短信服务商，将对应的验证码发送到手机上，阿里短信服务、腾讯短信通、华信、容联


        return ResponseResult.success();
    }
}
