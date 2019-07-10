package org.tpokora.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "firebase")
public class FirebaseProperties {

    private String serverKey;
    private String clientToken;

    private final Map<String, String> firebase = new HashMap<>();

    public String getServerKey() {
        return serverKey;
    }

    public void setServerKey(String serverKey) {
        this.serverKey = serverKey;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public Map<String, String> getFirebase() {
        return firebase;
    }
}
