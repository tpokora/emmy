package org.tpokora.application;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = { "classpath:application-test.properties", "classpath:application-db-test.properties" })
public class TestApplication {

    @DisplayName("Test context loading")
    @Test
    public void contextLoads() {
    }
}
