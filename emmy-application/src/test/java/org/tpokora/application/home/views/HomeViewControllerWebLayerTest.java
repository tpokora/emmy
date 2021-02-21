package org.tpokora.application.home.views;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.tpokora.application.common.views.BaseViewControllerWebLayerTest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(HomeViewController.class)
public class HomeViewControllerWebLayerTest extends BaseViewControllerWebLayerTest {

    public static final String WELCOME_MESSAGE = "Welcome to Emmy Project!";
    public static final String ROOT_URL = "/home";

    @Test
    @WithMockUser
    void testHomeView() throws Exception {
        this.mockMvc.perform(get(ROOT_URL)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(WELCOME_MESSAGE)));
    }
}
