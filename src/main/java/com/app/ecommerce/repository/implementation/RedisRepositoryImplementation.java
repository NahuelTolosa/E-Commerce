package com.app.ecommerce.repository.implementation;

import com.app.ecommerce.config.ApplicationProperties;
import com.app.ecommerce.repository.interfaces.RedisRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisRepositoryImplementation<T> implements RedisRepository<T> {

    public static final String NAME_MAP = "map";
    private final RedisTemplate<String, T> redisTemplate;
    private final ApplicationProperties properties;
    private HashOperations<String, String, String> hashOperations;
    private final ObjectMapper mapper;

    @PostConstruct
    void setHashOperations() {
        hashOperations = this.redisTemplate.opsForHash();
        this.redisTemplate.expire(NAME_MAP, Duration.ofMillis(properties.getTimeOfLife()));
    }

    @Override
    public T save(String key, T data) {
        try {
            hashOperations.put(NAME_MAP, key, serializeItem(data));
            return data;
        } catch (JsonProcessingException e) { }
        return data;
    }

    @Override
    public T get(String key, Class<T> classValue) {
        try {
            var item = hashOperations.get(NAME_MAP, key);
            if (item == null) return null;
            return deserializeItem(item, classValue);
        } catch (JsonProcessingException e) { }
        return null;
    }

    private String serializeItem(T item) throws JsonProcessingException {
        return mapper.writeValueAsString(item);
    }

    private T deserializeItem(String jsonInput, Class<T> classValue) throws JsonProcessingException {
        return mapper.readValue(jsonInput, classValue);
    }
}
