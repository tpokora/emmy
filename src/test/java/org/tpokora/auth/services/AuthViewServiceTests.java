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
import org.springframework.ui.Model;
import org.tpokora.persistance.entity.users.Role;
import org.tpokora.persistance.entity.users.User;
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
    void testAuthViewServiceSignInView() {
        Assertions.assertEquals(SIGNIN_VIEW_TEMPLATE, this.authViewService.signInView(model));
    }

    @Test
    void testAuthViewServiceRolesView() {
        Assertions.assertEquals(ROLES_VIEW_TEMPLATE, this.authViewService.rolesView(model));
    }

    @Disabled("Configure Spring Security for testing")
    @Test
    void testAuthViewServiceRegisterNewUserView() {
        UserDetailsImpl userDetails = new UserDetailsImpl(new User("testUser", "testUser", "test@test.test"));
        Mockito.lenient().when(this.authService.createNewUser(any())).thenReturn(userDetails);
        Assertions.assertEquals(HOME_VIEW, this.authViewService.registerNewUserView(userDetails));
    }

    @Test
    void testAuthViewServiceCreateRole() {
        Role role = new Role("TEST_ROLE");
        Mockito.lenient().when(this.authService.createRole(any())).thenReturn(role);
        Assertions.assertNotNull(authViewService.createRole(role));
    }

    @Test
    void testAuthViewServiceCheckIfEmailExists() {
        Mockito.lenient().when(this.authService.checkIfEmailExists(any())).thenReturn(true);
        Assertions.assertTrue(authViewService.checkIfEmailExists("email@email.com"));
    }

    @Test
    void testAuthViewServiceCheckIfUserExists() {
        Mockito.lenient().when(this.authService.checkIfUserExists(any())).thenReturn(true);
        Assertions.assertTrue(authViewService.checkIfUserExists("username"));
    }

    @Test
    void testAuthViewServiceCheckIfRoleExists() {
        Mockito.lenient().when(this.authService.checkIfRoleExists(any())).thenReturn(true);
        Assertions.assertTrue(authViewService.checkIfRoleExists("TEST_ROLE"));
    }
}
