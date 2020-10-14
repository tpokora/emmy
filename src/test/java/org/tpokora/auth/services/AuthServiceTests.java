package org.tpokora.auth.services;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.tpokora.common.services.BaseServiceTest;
import org.tpokora.persistance.entity.users.Role;
import org.tpokora.persistance.entity.users.User;
import org.tpokora.users.model.UserDetailsImpl;
import org.tpokora.users.services.UserDetailsServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class AuthServiceTests extends BaseServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceTests.class);

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    private AuthService authService;

    @MockBean
    private Model model;

    @BeforeEach
    void setup() {
        this.authService = new AuthService(userDetailsService);
    }

    @Test
    void testAuthServiceIsNotNull() {
        Assertions.assertNotNull(this.authService);
    }

    @Test
    void testAuthServiceCreateNewUser() {
        User newUser = new User("testUser1", "testUser", "testUser@test.com");
        newUser.setId(1);
        newUser.setRoles(Sets.newHashSet(new Role("USER")));
        Mockito.lenient().when(userDetailsService.saveUser(any(), anyString())).thenReturn(newUser);
        UserDetails newUserDetails = this.authService.createNewUser(newUser);
        Assertions.assertEquals(newUser.getUsername(), newUserDetails.getUsername());
        Assertions.assertEquals(newUser.getPassword(), newUserDetails.getPassword());
        Assertions.assertEquals(newUser.getRoles().stream().findFirst().get().getName(), "USER");
    }

    @Test
    void testAuthServiceCreateRole() {
        Role newRole = new Role();
        newRole.setId(1);
        newRole.setName("TEST");

        Mockito.lenient().when(userDetailsService.createRole(any())).thenReturn(newRole);
        Role createRole = this.authService.createRole(newRole);
        Assertions.assertEquals(newRole.getId(), createRole.getId());
        Assertions.assertEquals(newRole.getName(), createRole.getName());
    }

    @Test
    void testAuthServiceCheckIfUserAndEmailExists() {
        String testUsername = "testUser";
        String testPassword = "testPassword";
        String testEmail = "testUser@test.com";
        User testUser = new User(testUsername, testPassword, testEmail);
        UserDetails userDetails = new UserDetailsImpl(testUser);
        Mockito.lenient().when(userDetailsService.loadUserByUsername(any())).thenReturn(userDetails);
        Assertions.assertTrue(authService.checkIfUserExists(testUsername));
        Mockito.lenient().when(userDetailsService.loadUserByEmail(any())).thenReturn(userDetails);
        Assertions.assertTrue(authService.checkIfEmailExists(testEmail));
    }

    @Test
    void testAuthServiceCheckIfRoleExists() {
        Role role = new Role("testRole");

        Mockito.lenient().when(userDetailsService.getRole(any())).thenReturn(role);
        Assertions.assertTrue(authService.checkIfRoleExists(role.getName()));
    }

    @Test
    void testAuthServiceCheckIfUsernameEmailRoleExists_throwException() {
        Assertions.assertFalse(authService.checkIfUserExists("notExistingUsername"));
        Assertions.assertFalse(authService.checkIfEmailExists("notExistingEmail"));
        Assertions.assertFalse(authService.checkIfRoleExists("notExistingRole"));
    }
}
