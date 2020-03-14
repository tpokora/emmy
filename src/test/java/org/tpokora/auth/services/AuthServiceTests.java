package org.tpokora.auth.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.ui.Model;
import org.tpokora.common.services.BaseServiceTest;
import org.tpokora.users.dao.RolesRepository;
import org.tpokora.users.dao.UserRepository;
import org.tpokora.users.model.User;
import org.tpokora.users.services.UserDetailsServiceImpl;

import static org.tpokora.auth.AuthConstatns.SIGNIN_VIEW_TEMPLATE;
import static org.tpokora.home.views.HomeViewConstants.HOME_VIEW;

public class AuthServiceTests extends BaseServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceTests.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private Model model;

    @Spy
    @InjectMocks
    private AuthService authService;

    @BeforeEach
    public void setup() {
        this.userDetailsService = new UserDetailsServiceImpl(userRepository, rolesRepository);
        this.authService = new AuthService(userDetailsService);
    }

    @Test
    public void testAuthServiceIsNotNull() {
        Assertions.assertNotNull(this.authService);
    }

    @Test
    public void testAuthServiceSinginView() {
        Assertions.assertEquals(SIGNIN_VIEW_TEMPLATE, this.authService.signinView(this.model));
    }

    @Disabled("Mock AuthService.createNewUser method")
    @Test
    public void testAuthServiceRegisterNewUserView() {
        User user = new User("testUser", "testUser", "test@test.test");
        Assertions.assertEquals(HOME_VIEW, this.authService.registerNewUserView(user));
    }
}
