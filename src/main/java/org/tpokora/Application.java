package org.tpokora;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.tpokora.config.properties.FirebaseProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.tpokora.config.properties.StormProperties;
import org.tpokora.config.properties.NotificationProperties;

@SpringBootApplication(scanBasePackages = { "org.tpokora" })
@EnableScheduling
@EnableJpaRepositories
@EnableTransactionManagement
@EnableConfigurationProperties({FirebaseProperties.class, NotificationProperties.class, StormProperties.class})
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}