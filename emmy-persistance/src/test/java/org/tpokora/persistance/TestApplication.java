package org.tpokora.persistance;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Disabled
@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = { "classpath:application-test.properties", "classpath:application-db-test.properties" })
public class TestApplication {

    @DisplayName("Test context loading")
    @Test
    void contextLoads() {
    }
}
