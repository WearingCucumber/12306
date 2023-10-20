package com.study.train.common.util;


import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;

/**
 * 封装hutool的JWT
 */
@Slf4j
public class JwtUtil {
    private static final String key  = "WearingCucumber";
    public static  String createToken(Long id,String mobile){
        DateTime now = DateTime.now();
        //将dateTime设为不可见性 防止下面的offset方法返回的对象和上面的使同一个对象导致两个时间信息被覆盖成同一个
        now.setMutable(false);
        DateTime expireTime = now.offset(DateField.HOUR, 24);
        HashMap<String, Object> payload = new HashMap<>();
        //签发时间
        payload.put(JWTPayload.ISSUED_AT,now);
        //过期时间
        payload.put(JWTPayload.EXPIRES_AT,expireTime);
        //生效时间
        payload.put(JWTPayload.NOT_BEFORE,now);
        //内容
        payload.put("id",id);
        payload.put("mobile",mobile);
        String token = JWTUtil.createToken(payload,key.getBytes());
        log.info("token: {}",token);
        return token;
    }
    public static boolean validate(String token){
        JWT jwt = JWTUtil.parseToken(token).setKey(key.getBytes());
        //validate 包含了verify
        boolean validate = jwt.validate(0);
        log.info("校验结果：{}",validate);
        return validate;
    }
    public static JSONObject getJSONObject(String token){
        JWT jwt = JWTUtil.parseToken(token).setKey(key.getBytes());
        JSONObject payloads = jwt.getPayloads();
        payloads.remove(JWTPayload.ISSUED_AT);
        payloads.remove(JWTPayload.EXPIRES_AT);
        payloads.remove(JWTPayload.NOT_BEFORE);
        log.info("payload:{}",payloads);
        return payloads;
    }


}
