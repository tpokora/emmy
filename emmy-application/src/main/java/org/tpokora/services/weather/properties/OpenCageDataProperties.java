package org.tpokora.services.weather.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties("")
public class OpenCageDataProperties {

    public static final String KEY = "key";

    private final Map<String, String> opencage = new HashMap<>();

    public Map<String, String> getOpencage() {
        return opencage;
    }

    public String getValue(String key) {
        return this.opencage.get(key);
    }

    public String setValue(String key, String value) {
        return this.opencage.put(key, value);
    }
}
