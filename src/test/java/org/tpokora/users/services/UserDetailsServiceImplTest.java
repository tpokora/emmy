package org.tpokora.users.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.tpokora.common.services.BaseServiceTest;
import org.tpokora.users.dao.RolesRepository;
import org.tpokora.users.dao.UserRepository;
import org.tpokora.users.model.Role;
import org.tpokora.users.model.User;

import java.util.HashSet;
import java.util.List;


public class UserDetailsServiceImplTest extends BaseServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImplTest.class);
    private static final int USERS_AMOUNT = 2;

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    private User userOne;
    private User userTwo;

    private Role roleUser;

    @BeforeEach
    public void setup() {
        roleUser = new Role("ROLE_USER");
        roleUser = rolesRepository.saveAndFlush(roleUser);

        userOne = new User("testUser1", "testPassword1", "test1@email.com");
        userOne.setRoles(new HashSet<>());
        userTwo = new User("testUser2", "testPassword2", "test2@email.com");
        userTwo.setRoles(new HashSet<>());
        saveUser(userOne);
        saveUser(userTwo);
        userDetailsService = new UserDetailsServiceImpl(userRepository, rolesRepository);

    }

    @AfterEach
    public void teardown() {
        userRepository.deleteAll();
        rolesRepository.deleteAll();
    }

    @Test
    public void testRolesRepositoryGetRoles() {
        List<Role> allRoles = rolesRepository.findAll();
        Assertions.assertFalse(allRoles.isEmpty());
    }

    @Test
    public void testRolesRepositoryGetRoleByName() {
        Assertions.assertNotNull(rolesRepository.findByName(roleUser.getName()).get());
    }

    @Test
    public void testUserRepositoryGetOne() {
        User checkUser = userRepository.getOne(userOne.getId());
        Assertions.assertEquals(userOne.getId(), checkUser.getId());
        Assertions.assertEquals(userOne.getUsername(), checkUser.getUsername());
        Assertions.assertEquals(userOne.getEmail(), checkUser.getEmail());
        Assertions.assertEquals(userOne.getRoles(), checkUser.getRoles());
        Assertions.assertEquals(userOne.getPassword(), checkUser.getPassword());
    }

    @Test
    public void testUserRepositoryFindAll() {
        List<User> allUsers = userRepository.findAll();
        Assertions.assertEquals(USERS_AMOUNT, allUsers.size());
    }

    @Test
    public void testUserDetailsServiceGetUserDetailByUsername() {
        UserDetails fetchedUserDetails = userDetailsService.loadUserByUsername(userOne.getUsername());
        Assertions.assertEquals(userOne.getUsername(), fetchedUserDetails.getUsername());
        Assertions.assertEquals(userOne.getPassword(), fetchedUserDetails.getPassword());
    }

    @Test
    public void testUserDetailsServiceGetNotExistingUserDetailByUsername_expectedNull() {
        UserDetails fetchedUserDetails = userDetailsService.loadUserByUsername("notExistingUser");
        Assertions.assertNull(fetchedUserDetails);
    }

    @Test
    public void testUserDetailsServiceGetNotExistingUserDetailByEmail_expectedNull() {
        UserDetails fetchedUserDetails = userDetailsService.loadUserByEmail("notExistingUserEmail");
        Assertions.assertNull(fetchedUserDetails);
    }

    @Test
    public void testUserDetailsServiceGetUserDetailByEmail() {
        UserDetails fetchedUserDetails = userDetailsService.loadUserByEmail(userOne.getEmail());
        Assertions.assertEquals(userOne.getUsername(), fetchedUserDetails.getUsername());
        Assertions.assertEquals(userOne.getPassword(), fetchedUserDetails.getPassword());
    }

    @Test
    public void testUserDetailsServiceGetAllUsers() {
        List<User> allUsers = userDetailsService.getAllUsers();
        Assertions.assertEquals(USERS_AMOUNT, allUsers.size());
    }

    @Test
    public void testUserDetailsServiceSaveUser() {
        User newUser = new User("test1", "test2", "test3@email.com");
        String notHashedPassword = newUser.getPassword();
        User createdUser = userDetailsService.saveUser(newUser, roleUser.getName());

        Assertions.assertNotNull(createdUser.getId());
        Assertions.assertEquals(newUser.getUsername(), createdUser.getUsername());
        Assertions.assertEquals(newUser.getEmail(), createdUser.getEmail());
        Assertions.assertNotEquals(notHashedPassword, createdUser.getPassword());
        Assertions.assertNotNull(createdUser.getRoles());
        Assertions.assertNotNull(createdUser.getRoles().stream().filter(role -> roleUser.getName().equals(role.getName())).findFirst().get());
    }

    @Test
    public void testGetAllRoles() {
        Assertions.assertEquals(rolesRepository.findAll().size(), userDetailsService.getAllRoles().size());
    }

    @Test
    public void testGetRole() {
        Assertions.assertEquals(roleUser.getName(), userDetailsService.getRole(roleUser.getName()).getName());
    }

    @Test
    public void testCreateRole() {
        String testRoleName = "TEST_ROLE";
        Role role = new Role(testRoleName);
        Role newRole = userDetailsService.createRole(role);
        Assertions.assertEquals(testRoleName, newRole.getName());

    }

    private User saveUser(User user) {
        return userRepository.saveAndFlush(user);
    }

}
