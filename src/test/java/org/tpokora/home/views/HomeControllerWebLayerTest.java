package org.tpokora.home.views;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.tpokora.config.TestH2DatabaseConfiguration;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeViewController.class)
@TestPropertySource(locations = { "classpath:application-test.properties", "classpath:application-db-test.properties"})
@Import(TestH2DatabaseConfiguration.class)
public class HomeControllerWebLayerTest {

    public static final String HOME = "home";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHome() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(HOME)));
    }
}
