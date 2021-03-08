package org.tpokora.application.common.services;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.tpokora.application.TestApplication;

@SpringBootTest(classes = {TestApplication.class})
@TestPropertySource(locations = { "classpath:application-test.yaml", "classpath:application-db-test.yaml"})
public abstract class BaseServiceTest {

}
