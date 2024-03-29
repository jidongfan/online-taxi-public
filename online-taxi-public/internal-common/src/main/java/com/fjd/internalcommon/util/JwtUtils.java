package com.fjd.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fjd.internalcommon.dto.TokenResult;
import org.apache.commons.collections.map.HashedMap;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;

/**
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/5 19:21
 * @desc:
 */
public class JwtUtils {

    //言 security
    private static final String SIGN = "CPFfjd!@#$$";

    public final static String JWT_KEY_PHONE = "phone";

    public final static String JWT_KEY_IDENTITY = "identity"; //一个phone登录 分为 乘客端1 和 司机端2

    private static final String JWT_KEY_TYPE = "tokenType";

    private static final String JWT_TOKEN_TIME = "tokenTime";

    //生成token map用户名 密码
    public static String generatorToken(String phone, String identity, String tokenType){
        Map<String, String> map = new HashMap<>();
        map.put(JWT_KEY_PHONE, phone);
        map.put(JWT_KEY_IDENTITY, identity);
        map.put(JWT_KEY_TYPE, tokenType);
        //token过期时间，方式生产的token重复
        map.put(JWT_TOKEN_TIME, Calendar.getInstance().getTime().toString());

        JWTCreator.Builder builder = JWT.create();
        //整个map放入builder中
        map.forEach((k, v) -> {
            builder.withClaim(k,v);
        });

        //整合过期时间，用redis的有效期控制就好了
        //builder.withExpiresAt(date);

        //生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));

        return sign;
    }

    //解析token
    public static TokenResult parseToken(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        String phone = verify.getClaim(JWT_KEY_PHONE).asString();
        String identity = verify.getClaim(JWT_KEY_IDENTITY).asString();

        TokenResult tokenResult = new TokenResult();
        tokenResult.setPhone(phone);
        tokenResult.setIdentity(identity);
        return tokenResult;
    }

    /**
     *校验token 主要判断token是否异常
     * @param token
     * @return
     */
    public static TokenResult checkToken(String token){
        TokenResult tokenResult = null;
        try {
            tokenResult = JwtUtils.parseToken(token);
        } catch (SignatureVerificationException e) {

        }
        return tokenResult;
    }

    public static void main(String[] args) {
        String token = generatorToken("123456789", "1", "accessToken");
        System.out.println(token);
        TokenResult parseToken = parseToken(token);
        System.out.println(parseToken);

    }

}
