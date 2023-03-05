package com.fjd.internalcommon.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;

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

    //生成token map用户名 密码
    public static String generatorToken(Map<String, String> map){
        //token过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, 1);
        Date date = calendar.getTime();

        JWTCreator.Builder builder = JWT.create();
        //整个map放入builder中
        map.forEach((k, v) -> {
            builder.withClaim(k,v);
        });

        //整合过期时间
        builder.withExpiresAt(date);

        //生成token
        String sign = builder.sign(Algorithm.HMAC256(SIGN));

        return sign;
    }

    //解析token

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("name", "zhang san");
        map.put("age", "18");
        String s = generatorToken(map);
        System.out.println("生成的token: " + s);
    }
}
