package org.tpokora.home.views;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.tpokora.common.views.BaseViewTest;
import org.tpokora.users.dao.UserRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.tpokora.home.views.HomeViewConstants.*;

public class HomeViewControllerTest extends BaseViewTest {

    @Autowired
    private UserRepository userRepository;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new HomeViewController()).build();
    }

    // TODO: Fix tests
    @Test
    public void testLoginPage() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get(LOGIN_VIEW_URL))
                .andExpect(status().isOk())
                .andExpect(view().name(LOGIN_VIEW))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}
