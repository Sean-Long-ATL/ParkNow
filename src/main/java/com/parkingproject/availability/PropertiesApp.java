package com.parkingproject.availability;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring")
@PropertySource("file:C:\\Server\\server.properties")
@Data
public class PropertiesApp
{
    private String dll;
    private String url;
    private String username;
    private String password;
}
