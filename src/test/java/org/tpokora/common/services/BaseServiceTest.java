package org.tpokora.common.services;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.tpokora.TestApplication;
import org.tpokora.config.TestH2DatabaseConfiguration;

@SpringBootTest(classes = {TestApplication.class, TestH2DatabaseConfiguration.class })
@TestPropertySource(locations = { "classpath:application-test.properties", "classpath:application-db-test.properties"})
public abstract class BaseServiceTest {

}
