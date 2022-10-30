package com.spring.ws.spring.redis;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;


public class RedisLockUtil {

    private StringRedisTemplate redisTemplate;

    /**
     * 构造函数
     * @param redisTemplate
     */
    public RedisLockUtil(StringRedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    /**
     * 上锁
     * @param key
     * @return 之前已经存在返回false,不存在返回true 默认3秒时间
     */
    public boolean lock(String key) {
        return redisTemplate.opsForValue().setIfAbsent(key, key,3L, TimeUnit.SECONDS);
    }

    /**
     * 上锁
     * @param key
     * @param expireTime 过期时间，单位 秒
     * @return 之前已经存在返回false,不存在返回true
     */
    public boolean lock(String key,long expireTime) {
        return redisTemplate.opsForValue().setIfAbsent(key,key,expireTime,TimeUnit.SECONDS);
    }

    /**
     * 解锁
     * @param key
     * @return
     */
    public boolean unlock(String key){
       return redisTemplate.delete(key);
    }

}
