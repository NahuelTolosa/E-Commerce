package com.app.ecommerce.repository.interfaces;

public interface RedisRepository<T> {

    T save(String key, T data);
    T get(String key, Class<T> classValue);

}
