package com.hanghae.bulletbox.common.redis;

import lombok.RequiredArgsConstructor;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
@RequiredArgsConstructor
public class RedisUtil {
    private final RedisTemplate<String, String> redisTemplate;

    public String getData(String key) {
        // key를 통해 value(데이터)를 얻는다.
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public void setDataExpire(String key, String value, long duration) {
        //  duration 동안 (key, value)를 저장한다.
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        Duration expireDuration = Duration.ofMillis(duration);
        valueOperations.set(key, value, expireDuration);
    }

    public void deleteData(String key) {
        // 데이터 삭제
        redisTemplate.delete(key);
    }
}