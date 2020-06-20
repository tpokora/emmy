package org.tpokora.home.views;

import org.junit.jupiter.api.Test;
import org.tpokora.common.views.BasicViewControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class HomeViewControllerTest extends BasicViewControllerTest {


    @Test
    public void testHome() throws Exception {
        mockMvc.perform(get("/home")).andDo(print()).andExpect(status().isOk()).andExpect(content().string("Welcome to Emmy App!"));
    }

}