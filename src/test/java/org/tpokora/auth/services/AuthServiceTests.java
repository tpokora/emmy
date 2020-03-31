package org.tpokora.auth.services;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.tpokora.common.services.BaseServiceTest;
import org.tpokora.users.dao.RolesRepository;
import org.tpokora.users.dao.UserRepository;
import org.tpokora.users.model.Role;
import org.tpokora.users.model.User;
import org.tpokora.users.model.UserTestUtils;
import org.tpokora.users.services.UserDetailsServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.tpokora.auth.AuthConstatns.SIGNIN_VIEW_TEMPLATE;
import static org.tpokora.home.views.HomeViewConstants.HOME_VIEW;
import static org.tpokora.users.views.UsersViewConstants.ROLES_VIEW_TEMPLATE;

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
    public void testAuthServiceSignInView() {
        Assertions.assertEquals(SIGNIN_VIEW_TEMPLATE, this.authService.signInView(this.model));
    }

    @Test
    public void testAuthServiceRolesView() {
        Assertions.assertEquals(ROLES_VIEW_TEMPLATE, this.authService.rolesView(this.model));
    }

    @Disabled("Mock AuthService.createNewUser method")
    @Test
    public void testAuthServiceRegisterNewUserView() {
        User user = new User("testUser", "testUser", "test@test.test");
        Assertions.assertEquals(HOME_VIEW, this.authService.registerNewUserView(user));
    }

    @Disabled("Fix Mocking userDetailsServiceMock.saveUser()")
    @Test
    public void testAuthServiceCreateNewUser() {
        User newUser = UserTestUtils.createTestUser("testUser1", "testUser", "testUser@test.com");
        newUser.setRoles(Sets.newHashSet(new Role("TEST")));
        UserDetailsServiceImpl userDetailsServiceMock = Mockito.mock(UserDetailsServiceImpl.class);
        when(userDetailsServiceMock.saveUser(any(), anyString())).thenReturn(newUser);
        UserDetails newUserDetails = this.authService.createNewUser(newUser);
        Assertions.assertEquals(newUser.getUsername(), newUserDetails.getUsername());
        Assertions.assertEquals(newUser.getPassword(), newUserDetails.getPassword());
    }
}
