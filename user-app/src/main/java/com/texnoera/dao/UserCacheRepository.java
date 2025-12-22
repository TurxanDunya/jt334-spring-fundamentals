package com.texnoera.dao;

import com.texnoera.dao.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class UserCacheRepository {

    private final RedisTemplate<String, User> redisTemplate;

    @Value("${cache.redis.user.ttl}")
    Long ttl;

    public User read(String name) {
        return redisTemplate.opsForValue().get(name);
    }

    public void save(User user) {
        redisTemplate.opsForValue().set(user.getName(), user, ttl, TimeUnit.SECONDS);
    }

}
