package com.xeehoo.p2p.service.impl;

import com.xeehoo.p2p.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangzunhui on 2016/1/25.
 */
@Service("tokenService")
public class TokenServiceImpl implements TokenService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void put(String key, String val) {
        redisTemplate.opsForValue().set(key, val);
    }

    @Override
    public void put(String key, String val, int minutes) {
        redisTemplate.opsForValue().set(key, val, minutes, TimeUnit.MINUTES);
    }

    @Override
    public String[] getUserByToken(String token) {
        String val = get(token);
        if (val == null){
            return null;
        }
        String[] v = val.split(",");
        if (v == null || v.length != 2){
            return null;
        }

        return v;
    }

    @Override
    public Integer getUserId(String token) {
        String[] v = getUserByToken(token);
        if (v == null)
            return null;

        return Integer.parseInt(v[0]);
    }

    @Override
    public String get(String token){
        return (String)redisTemplate.opsForValue().get(token);
    }
}
