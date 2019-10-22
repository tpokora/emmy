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
import org.tpokora.users.dao.UserRepository;
import org.tpokora.users.model.User;

import java.util.HashSet;
import java.util.List;


public class UserDetailsServiceImplTest extends BaseServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImplTest.class);
    private static final int USERS_AMOUNT = 2;

    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepository userRepository;

    private User userOne;
    private User userTwo;

    @Before
    public void setup() {
        this.userOne = new User("testUser1", "testPassword1", "test1@email.com");
        this.userOne.setRoles(new HashSet<>());
        this.userTwo = new User("testUser2", "testPassword2", "test2@email.com");
        this.userTwo.setRoles(new HashSet<>());
        saveUser(this.userOne);
        saveUser(this.userTwo);
        this.userDetailsService = new UserDetailsServiceImpl(this.userRepository);
    }

    @After
    public void teardown() {
        this.userRepository.deleteAll();
    }

    @Test
    public void testUserRepositoryGetOne() {
        User checkUser = this.userRepository.getOne(userOne.getId());
        Assert.assertEquals(userOne.getId(), checkUser.getId());
        Assert.assertEquals(userOne.getUsername(), checkUser.getUsername());
        Assert.assertEquals(userOne.getPassword(), checkUser.getPassword());
        Assert.assertEquals(userOne.getEmail(), checkUser.getEmail());
        Assert.assertEquals(userOne.getRoles(), checkUser.getRoles());
    }

    @Test
    public void testUserRepositoryFindAll() {
        List<User> allUsers = this.userRepository.findAll();
        Assert.assertEquals(USERS_AMOUNT, allUsers.size());
    }

    @Test
    public void test_userDetailsServiceGetUserDetailByUsername() {
        UserDetails fetchedUserDetails = userDetailsService.loadUserByUsername(this.userOne.getUsername());
        Assert.assertEquals(this.userOne.getUsername(), fetchedUserDetails.getUsername());
        Assert.assertEquals(this.userOne.getPassword(), fetchedUserDetails.getPassword());
    }

    @Test
    public void test_userDetailsServiceGetAllUsers() {
        List<User> allUsers = this.userDetailsService.getAllUsers();
        Assert.assertEquals(USERS_AMOUNT, allUsers.size());
    }

    private User saveUser(User user) {
        return this.userRepository.saveAndFlush(user);
    }

}
