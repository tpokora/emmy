package org.tpokora.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties("")
public class NotificationProperties {

    public static final String COORDINATE_X = "coordinateX";
    public static final String COORDINATE_Y = "coordinateY";

    private final Map<String, String> notifications = new HashMap<>();

    public Map<String, String> getNotifications() {
        return notifications;
    }

    public String getValue(String key) {
        return this.notifications.get(key);
    }

    public String setValue(String key, String value) {
        return this.notifications.put(key, value);
    }
}
