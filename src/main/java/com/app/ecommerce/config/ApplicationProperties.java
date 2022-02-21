package com.app.ecommerce.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties
public class ApplicationProperties {

    @Value(value = "${spring.redis.host:localhost}")
    private String host;
    @Value(value = "${spring.redis.port:6379}")
    private Integer port;
    @Value(value = "${spring.redis.timeOfLife:60000}")
    private Integer timeOfLife;
}
