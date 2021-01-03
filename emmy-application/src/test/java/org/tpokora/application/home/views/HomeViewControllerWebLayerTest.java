package org.tpokora.application.home.views;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HomeViewController.class)
public class HomeViewControllerWebLayerTest {

    public static final String WELCOME_MESSAGE = "Welcome to Emmy Project!";
    public static final String ROOT_URL = "/";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void shouldReturnDefaultMessage() throws Exception {

        this.mockMvc.perform(get(ROOT_URL)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(WELCOME_MESSAGE)));
    }
}
