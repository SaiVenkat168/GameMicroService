package com.example.UserService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class SessionService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String SESSION_PREFIX = "session:";

    public void createSession(String sessionId, Map<String, String> sessionData) {
        String key = SESSION_PREFIX + sessionId;
        redisTemplate.opsForHash().putAll(key, sessionData);
        // Set an appropriate expiration time if needed
        redisTemplate.expire(key, 3, TimeUnit.MINUTES);
    }

    public Map<Object, Object> getSession(String sessionId) {
        String key = SESSION_PREFIX + sessionId;
        return redisTemplate.opsForHash().entries(key);
    }

    public void deleteSession(String sessionId) {
        String key = SESSION_PREFIX + sessionId;
        redisTemplate.delete(key);
    }
}
