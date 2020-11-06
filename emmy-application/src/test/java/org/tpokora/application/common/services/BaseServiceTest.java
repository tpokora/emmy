package org.tpokora.application.common.services;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.tpokora.application.TestApplication;
import org.tpokora.application.config.TestH2DatabaseConfiguration;

@SpringBootTest(classes = {TestApplication.class, TestH2DatabaseConfiguration.class })
@TestPropertySource(locations = { "classpath:application-test.properties", "classpath:application-db-test.properties"})
public abstract class BaseServiceTest {

}
