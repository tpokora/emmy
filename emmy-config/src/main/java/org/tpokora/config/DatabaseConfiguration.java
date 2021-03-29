package org.tpokora.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:application-db.properties")
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories("org.tpokora.persistance.repositories.*")
@EntityScan("org.tpokora.persistance.entity.*")
public class DatabaseConfiguration {
}
