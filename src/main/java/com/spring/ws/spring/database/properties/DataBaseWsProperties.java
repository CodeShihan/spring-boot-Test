package com.spring.ws.spring.database.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.datasource.databasews")
@Component
@Data
public class DataBaseWsProperties {
    private String url;

    private String username;

    private String password;

    private String driverClassName;

}
