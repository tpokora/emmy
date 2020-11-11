package org.tpokora.application.home.views;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// TODO: Fix HomeWebLayer test
public class HomeControllerWebLayerTest {

    public static final String HOME = "home";

    @Autowired
    private MockMvc mockMvc;

    @Disabled
    @Test
    public void testHome() throws Exception {
        this.mockMvc.perform(get("/")).andExpect(status().isOk())
                .andExpect(content().string(containsString(HOME)));
    }
}
