package org.tpokora.application.common.services;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.tpokora.application.TestApplication;

@SpringBootTest(classes = {TestApplication.class})
@ActiveProfiles("db-test")
public abstract class BaseServiceTest {

}
