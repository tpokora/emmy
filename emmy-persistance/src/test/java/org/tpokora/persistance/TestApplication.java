package org.tpokora.persistance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@TestPropertySource(locations = { "classpath:application-test.properties", "classpath:application-db-test.properties" })
@EnableJpaRepositories("org.tpokora.persistance.repositories")
@EntityScan("org.tpokora.persistance.entity.*")
@EnableAutoConfiguration(exclude = JpaRepositoriesAutoConfiguration.class)
public class TestApplication {

    @Test
    void contextLoads() {
    }
}
