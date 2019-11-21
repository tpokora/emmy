package org.tpokora;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = { "classpath:application-test.properties", "classpath:application-db-test.properties" })
public class TestApplication {

    @DisplayName("Test context loading")
    @Test
    void contextLoads() {
    }
}
