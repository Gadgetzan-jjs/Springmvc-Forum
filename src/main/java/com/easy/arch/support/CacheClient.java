package com.easy.arch.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CacheClient {
    @Autowired
    private RedisTemplate<String ,Object> redisTemplate;

    public Object getValue(String key){
        if (key==null||"".equals(key)){
            return null;
        }else{
            return redisTemplate.opsForValue().get(key);
        }
    }
    public void  putValue(String key,Object o,int expire){
        if ("".equals(key)){
            redisTemplate.opsForValue().set(key,o,expire, TimeUnit.SECONDS);
        }
    }
}
