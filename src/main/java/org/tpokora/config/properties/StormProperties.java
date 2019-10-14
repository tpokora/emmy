package org.tpokora.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties("")
public class StormProperties {

    public static final String KEY = "key";

    private final Map<String, String> storm = new HashMap<>();

    public Map<String, String> getStorm() {
        return storm;
    }

    public String getValue(String value) {
        return this.storm.get(value);
    }

    public String setValue(String key, String value) {
        return this.storm.put(key, value);
    }
}