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
        this.roleUser = new Role("ROLE_USER");
        this.roleUser = this.rolesRepository.saveAndFlush(this.roleUser);

        this.userOne = new User("testUser1", "testPassword1", "test1@email.com");
        this.userOne.setRoles(new HashSet<>());
        this.userTwo = new User("testUser2", "testPassword2", "test2@email.com");
        this.userTwo.setRoles(new HashSet<>());
        saveUser(this.userOne);
        saveUser(this.userTwo);
        this.userDetailsService = new UserDetailsServiceImpl(this.userRepository, this.rolesRepository);

    }

    @AfterEach
    public void teardown() {
        this.userRepository.deleteAll();
        this.rolesRepository.deleteAll();
    }

    @Test
    public void testRolesRepositoryGetRoles() {
        List<Role> allRoles = this.rolesRepository.findAll();
        Assertions.assertFalse(allRoles.isEmpty());
    }

    @Test
    public void testRolesRepositoryGetRoleByName() {
        Assertions.assertNotNull(this.rolesRepository.findByName(this.roleUser.getName()).get());
    }

    @Test
    public void testUserRepositoryGetOne() {
        User checkUser = this.userRepository.getOne(userOne.getId());
        Assertions.assertEquals(userOne.getId(), checkUser.getId());
        Assertions.assertEquals(userOne.getUsername(), checkUser.getUsername());
        Assertions.assertEquals(userOne.getEmail(), checkUser.getEmail());
        Assertions.assertEquals(userOne.getRoles(), checkUser.getRoles());
        Assertions.assertEquals(userOne.getPassword(), checkUser.getPassword());
    }

    @Test
    public void testUserRepositoryFindAll() {
        List<User> allUsers = this.userRepository.findAll();
        Assertions.assertEquals(USERS_AMOUNT, allUsers.size());
    }

    @Test
    public void testUserDetailsServiceGetUserDetailByUsername() {
        UserDetails fetchedUserDetails = userDetailsService.loadUserByUsername(this.userOne.getUsername());
        Assertions.assertEquals(this.userOne.getUsername(), fetchedUserDetails.getUsername());
        Assertions.assertEquals(this.userOne.getPassword(), fetchedUserDetails.getPassword());
    }

    @Test
    public void testUserDetailsServiceGetAllUsers() {
        List<User> allUsers = this.userDetailsService.getAllUsers();
        Assertions.assertEquals(USERS_AMOUNT, allUsers.size());
    }

    @Test
    public void testUserDetailsServiceSaveUser() {
        User newUser = new User("test1", "test2", "test3@email.com");
        String notHashedPassword = newUser.getPassword();
        User createdUser = this.userDetailsService.saveUser(newUser, this.roleUser.getName());

        Assertions.assertNotNull(createdUser.getId());
        Assertions.assertEquals(newUser.getUsername(), createdUser.getUsername());
        Assertions.assertEquals(newUser.getEmail(), createdUser.getEmail());
        Assertions.assertNotEquals(notHashedPassword, createdUser.getPassword());
        Assertions.assertNotNull(createdUser.getRoles());
        Assertions.assertNotNull(createdUser.getRoles().stream().filter(role -> this.roleUser.getName().equals(role.getName())).findFirst().get());
    }

    @Test
    public void testGetAllRoles() {
        Assertions.assertEquals(this.rolesRepository.findAll().size(), this.userDetailsService.getAllRoles().size());
    }

    private User saveUser(User user) {
        return this.userRepository.saveAndFlush(user);
    }

}
