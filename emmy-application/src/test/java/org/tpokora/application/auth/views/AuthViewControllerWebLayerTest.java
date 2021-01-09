package org.tpokora.application.auth.views;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.tpokora.application.auth.services.AuthService;
import org.tpokora.application.auth.services.AuthViewService;
import org.tpokora.application.common.views.BaseViewControllerWebLayerTest;
import org.tpokora.application.users.services.UserDetailsServiceImpl;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AuthViewController.class)
@TestPropertySource(locations = {"classpath:application-test.properties", "classpath:application-db-test.properties"})
class AuthViewControllerWebLayerTest extends BaseViewControllerWebLayerTest {

    public static final String LOGIN = "login";
    public static final String LOGIN_MSG = "Please Log in";

    @MockBean
    private AuthViewService authViewService;

    @MockBean(name = "userDetailsService")
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private AuthService authService;

    @Test
    @WithAnonymousUser
    void testLogin() throws Exception {
        this.mockMvc.perform(get("/login")).andExpect(status().isOk())
                .andExpect(content().string(containsString(LOGIN_MSG)));
    }

    @Disabled
    @Test
    @WithMockUser
    void testSignin() throws Exception {
        this.mockMvc.perform(get("/signin")).andExpect(status().isOk())
                .andExpect(content().string(containsString("auth/signin")));
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