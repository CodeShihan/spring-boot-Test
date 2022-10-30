package com.spring.ws.spring.database.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.datasource.databasekk")
@Component
@Data
public class DataBaseKkProperties {
    private String url;

    private String username;

    private String password;

    private String driverClassName;

}
