package org.tpokora.auth.views;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.tpokora.auth.services.AuthService;
import org.tpokora.auth.services.AuthViewService;
import org.tpokora.config.TestH2DatabaseConfiguration;
import org.tpokora.users.services.UserDetailsServiceImpl;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AuthViewController.class)
@TestPropertySource(locations = {"classpath:application-test.properties", "classpath:application-db-test.properties"})
@Import(TestH2DatabaseConfiguration.class)
class AuthViewControllerWebLayerTest {

    public static final String LOGIN = "login";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthViewService authViewService;

    @MockBean(name = "userDetailsService")
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private AuthService authService;

    @Test
    void testLogin() throws Exception {
        this.mockMvc.perform(get("/login")).andExpect(status().isOk())
                .andExpect(content().string(containsString(LOGIN)));
    }

    // TODO: Fix Authentication tests
    @Disabled
    @Test
    void testSignin() throws Exception {
        this.mockMvc.perform(get("/signin")).andExpect(status().isOk())
                .andExpect(content().string(containsString("auth/login")));
    }

    @Disabled
    @Test
    void testAddUser() {
        // TODO: Fix Authentication tests
    }

    @Disabled
    @Test
    void testAddRole() {
        // TODO: Fix Authentication tests
    }
}