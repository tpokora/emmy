package org.tpokora.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties("")
public class AppProperties {

    private final Map<String, String> firebase = new HashMap<>();
    private final Map<String, String> storm = new HashMap<>();
    private final Map<String, String> notification = new HashMap<>();

    public Map<String, String> getFirebase() {
        return firebase;
    }

    public Map<String, String> getStorm() {
        return storm;
    }

    public Map<String, String> getNotification() { return notification; }
}