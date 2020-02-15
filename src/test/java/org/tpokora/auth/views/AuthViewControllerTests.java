package org.tpokora.auth.views;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import javax.persistence.EntityManagerFactory;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.tpokora.users.views.UsersViewConstants.SIGNIN_VIEW;
import static org.tpokora.users.views.UsersViewConstants.SIGNIN_VIEW_URL;

@Disabled
@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthViewController.class)
public class AuthViewControllerTests {

    @Autowired
    protected EntityManagerFactory entityManagerFactory;

    @Autowired
    private MockMvc mockMvc;

    // @TODO fix test
    @Test
    void testSignInPage() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get(SIGNIN_VIEW_URL))
                .andExpect(status().isOk())
                .andExpect(view().name(SIGNIN_VIEW))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
