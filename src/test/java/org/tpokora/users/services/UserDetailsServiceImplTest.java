package org.tpokora.users.services;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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

    @Before
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

    @After
    public void teardown() {
        this.userRepository.deleteAll();
        this.rolesRepository.deleteAll();
    }

    @Test
    public void testRolesRepositoryGetRoles() {
        List<Role> allRoles = this.rolesRepository.findAll();
        Assert.assertFalse(allRoles.isEmpty());
    }

    @Test
    public void testRolesRepositoryGetRoleByName() {
        Assert.assertNotNull(this.rolesRepository.findByName(this.roleUser.getName()).get());
    }

    @Test
    public void testUserRepositoryGetOne() {
        User checkUser = this.userRepository.getOne(userOne.getId());
        Assert.assertEquals(userOne.getId(), checkUser.getId());
        Assert.assertEquals(userOne.getUsername(), checkUser.getUsername());
        Assert.assertEquals(userOne.getEmail(), checkUser.getEmail());
        Assert.assertEquals(userOne.getRoles(), checkUser.getRoles());
        Assert.assertEquals(userOne.getPassword(), checkUser.getPassword());
    }

    @Test
    public void testUserRepositoryFindAll() {
        List<User> allUsers = this.userRepository.findAll();
        Assert.assertEquals(USERS_AMOUNT, allUsers.size());
    }

    @Test
    public void testUserDetailsServiceGetUserDetailByUsername() {
        UserDetails fetchedUserDetails = userDetailsService.loadUserByUsername(this.userOne.getUsername());
        Assert.assertEquals(this.userOne.getUsername(), fetchedUserDetails.getUsername());
        Assert.assertEquals(this.userOne.getPassword(), fetchedUserDetails.getPassword());
    }

    @Test
    public void testUserDetailsServiceGetAllUsers() {
        List<User> allUsers = this.userDetailsService.getAllUsers();
        Assert.assertEquals(USERS_AMOUNT, allUsers.size());
    }

    @Test
    public void testUserDetailsServiceSaveUser() {
        User newUser = new User("test1", "test2", "test3@email.com");
        String notHashedPassword = newUser.getPassword();
        User createdUser = this.userDetailsService.saveUser(newUser, this.roleUser.getName());

        Assert.assertNotNull(createdUser.getId());
        Assert.assertEquals(newUser.getUsername(), createdUser.getUsername());
        Assert.assertEquals(newUser.getEmail(), createdUser.getEmail());
        Assert.assertNotEquals(notHashedPassword, createdUser.getPassword());
        Assert.assertNotNull(createdUser.getRoles());
        Assert.assertNotNull(createdUser.getRoles().stream().filter(role -> this.roleUser.getName().equals(role.getName())).findFirst().get());
    }

    private User saveUser(User user) {
        return this.userRepository.saveAndFlush(user);
    }

}
