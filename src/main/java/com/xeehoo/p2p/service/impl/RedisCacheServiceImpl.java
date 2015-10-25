package com.xeehoo.p2p.service.impl;

import com.xeehoo.p2p.service.LoanCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by wangzunhui on 2015/9/28.
 */
@Service("cacheService")
public class RedisCacheServiceImpl implements LoanCacheService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void setMobileAuthCode(String mobile, String authCode) {
        redisTemplate.opsForValue().set(mobile, authCode, 5, TimeUnit.MINUTES);
    }

    @Override
    public String getMobileAuthCode(String mobile) {
        return (String)redisTemplate.opsForValue().get(mobile);
    }

    @Override
    public int addUserPwdErrorTimes(String host){
        Long errorTimes = redisTemplate.opsForValue().increment(host, 1L);
        if (errorTimes == 1) {
            redisTemplate.expire(host, 3L, TimeUnit.MINUTES);
        }

        return errorTimes.intValue();
    }

    @Override
    public int getUserPwdErrorTimes(String host){
        ValueOperations<String, Long> vo = redisTemplate.opsForValue();
        Long errorTimes = vo.get(host);
        if (errorTimes == null) {
            return 0;
        }

        return errorTimes.intValue();
    }
}
