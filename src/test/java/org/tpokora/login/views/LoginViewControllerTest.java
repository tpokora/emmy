package org.tpokora.login.views;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.tpokora.common.views.BaseViewTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.tpokora.users.views.UsersViewConstants.SIGNIN_VIEW;
import static org.tpokora.users.views.UsersViewConstants.SIGNIN_VIEW_URL;

public class LoginViewControllerTest extends BaseViewTest {

    private LoginViewController loginViewController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.loginViewController = new LoginViewController();
        this.mockMvc = MockMvcBuilders.standaloneSetup(loginViewController).build();
    }

    // @TODO fix tests
    @Test
    public void testSignInPage() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get(SIGNIN_VIEW_URL))
                .andExpect(status().isOk())
                .andExpect(view().name(SIGNIN_VIEW))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
