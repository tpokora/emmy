package org.tpokora.home.views;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.tpokora.TestApplication;
import org.tpokora.config.TestH2DatabaseConfiguration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = {TestApplication.class, TestH2DatabaseConfiguration.class })
@AutoConfigureMockMvc
@TestPropertySource(locations = { "classpath:application-test.properties", "classpath:application-db-test.properties"})
class HomeViewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHome() throws Exception {
        mockMvc.perform(get("/home")).andDo(print()).andExpect(status().isOk()).andExpect(content().string("Welcome to Emmy App!"));
    }

}