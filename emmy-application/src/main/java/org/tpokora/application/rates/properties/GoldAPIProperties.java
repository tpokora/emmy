package org.tpokora.application.rates.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:application.yaml")
@ConfigurationProperties()
public class GoldAPIProperties {

    public static final String KEY = "key";

    private final Map<String, String> goldapi = new HashMap<>();

    public Map<String, String> getGoldapi() {
        return goldapi;
    }

    public String getValue(String key) {
        return this.goldapi.get(key);
    }

    public String setValue(String key, String value) {
        return this.goldapi.put(key, value);
    }
}
