package org.tpokora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.tpokora.config.properties.FirebaseProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.tpokora.config.properties.AppProperties;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({FirebaseProperties.class, AppProperties.class})
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}