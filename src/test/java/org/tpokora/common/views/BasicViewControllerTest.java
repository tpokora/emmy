package org.tpokora.common.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.tpokora.TestApplication;
import org.tpokora.config.TestH2DatabaseConfiguration;

@SpringBootTest(classes = {TestApplication.class, TestH2DatabaseConfiguration.class })
@AutoConfigureMockMvc
@TestPropertySource(locations = { "classpath:application-test.properties", "classpath:application-db-test.properties"})
abstract public class BasicViewControllerTest {

    @Autowired
    protected MockMvc mockMvc;
}
