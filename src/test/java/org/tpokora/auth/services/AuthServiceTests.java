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
import org.tpokora.users.model.Role;
import org.tpokora.users.model.User;
import org.tpokora.users.model.UserTestUtils;
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
    public void setup() {
        this.authService = new AuthService(userDetailsService);
    }

    @Test
    public void testAuthServiceIsNotNull() {
        Assertions.assertNotNull(this.authService);
    }

    @Test
    public void testAuthServiceCreateNewUser() {
        User newUser = UserTestUtils.createTestUser("testUser1", "testUser", "testUser@test.com");
        newUser.setId(1);
        newUser.setRoles(Sets.newHashSet(new Role("USER")));
        Mockito.lenient().when(userDetailsService.saveUser(any(), anyString())).thenReturn(newUser);
        UserDetails newUserDetails = this.authService.createNewUser(newUser);
        Assertions.assertEquals(newUser.getUsername(), newUserDetails.getUsername());
        Assertions.assertEquals(newUser.getPassword(), newUserDetails.getPassword());
        Assertions.assertEquals(newUser.getRoles().stream().findFirst().get().getName(), "USER");
    }

    @Test
    public void testAuthServiceCreateRole() {
        Role newRole = new Role();
        newRole.setId(1);
        newRole.setName("TEST");

        Mockito.lenient().when(userDetailsService.createRole(any())).thenReturn(newRole);
        Role createRole = this.authService.createRole(newRole);
        Assertions.assertEquals(newRole.getId(), createRole.getId());
        Assertions.assertEquals(newRole.getName(), createRole.getName());
    }
}
