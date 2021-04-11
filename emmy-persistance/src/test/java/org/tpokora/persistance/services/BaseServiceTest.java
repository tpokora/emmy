package org.tpokora.persistance.services;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.tpokora.persistance.TestApplication;

@SpringBootTest(classes = {TestApplication.class})
@ActiveProfiles("db-test")
public abstract class BaseServiceTest {

}
