package org.tpokora.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:application-db-dev.properties")
@EnableAutoConfiguration
@EnableTransactionManagement
@Profile("dev")
public class DatabaseDevConfiguration {
}
