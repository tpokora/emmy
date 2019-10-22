package org.tpokora.common.services;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.tpokora.TestApplication;
import org.tpokora.config.TestH2DatabaseConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestApplication.class, TestH2DatabaseConfiguration.class })
@TestPropertySource("classpath:application-test.properties")
public abstract class BaseServiceTest {

}
