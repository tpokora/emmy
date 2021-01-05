package org.tpokora.persistance.services;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.tpokora.persistance.TestApplication;

@SpringBootTest(classes = {TestApplication.class})
@TestPropertySource(locations = { "classpath:application-test.properties", "classpath:application-db-test.properties"})
public abstract class BaseServiceTest {

}
