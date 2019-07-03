package org.tpokora.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application-db.properties")
@EnableAutoConfiguration
@Profile("db")
public class DatabaseConfiguration {
}
