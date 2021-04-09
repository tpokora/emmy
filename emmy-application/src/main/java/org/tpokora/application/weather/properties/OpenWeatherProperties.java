package org.tpokora.application.weather.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:application.yaml")
@ConfigurationProperties("")
public class OpenWeatherProperties {

    public static final String HOST = "host";
    public static final String ID = "id";
    public static final String KEY = "key";

    private final Map<String, String> openweather = new HashMap<>();

    public Map<String, String> getOpenweather() {
        return openweather;
    }

    public String getValue(String key) {
        return this.openweather.get(key);
    }

    public String setValue(String key, String value) {
        return this.openweather.put(key, value);
    }
}
