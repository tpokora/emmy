package org.tpokora.auth.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.tpokora.users.model.User;
import org.tpokora.users.model.UserDetailsImpl;
import org.tpokora.users.services.UserDetailsServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.tpokora.auth.AuthConstatns.SIGNIN_VIEW_TEMPLATE;
import static org.tpokora.home.views.HomeViewConstants.HOME_VIEW;
import static org.tpokora.users.views.UsersViewConstants.ROLES_VIEW_TEMPLATE;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class AuthViewServiceTests {

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private AuthService authService;

    private AuthViewService authViewService;

    @Mock
    private Model model;

    @BeforeEach
    void setup() {
//        Authentication authentication = Mockito.mock(Authentication.class);
//        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
//        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
        authViewService = new AuthViewService(userDetailsService, authService);
    }

    @Test
    void testAuthServiceSignInView() {
        Assertions.assertEquals(SIGNIN_VIEW_TEMPLATE, this.authViewService.signInView(model));
    }

    @Test
    public void testAuthServiceRolesView() {
        Assertions.assertEquals(ROLES_VIEW_TEMPLATE, this.authViewService.rolesView(model));
    }

    @Disabled("Configure Spring Security for testing")
    @Test
    public void testAuthServiceRegisterNewUserView() {
        UserDetailsImpl userDetails = new UserDetailsImpl(new User("testUser", "testUser", "test@test.test"));
        Mockito.lenient().when(this.authService.createNewUser(any())).thenReturn(userDetails);
        Assertions.assertEquals(HOME_VIEW, this.authViewService.registerNewUserView(userDetails));
    }
}
