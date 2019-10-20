package org.tpokora.config.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TestProperties {

    @Value("${test.username}")
    private String username;

    public String getUsername() {
        return username;
    }

    @Value("${test.password}")
    private String password;

    public String getPassword() {
        return password;
    }
}
