package org.tpokora.users.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.tpokora.common.services.BaseServiceTest;
import org.tpokora.users.dao.UserRepository;
import org.tpokora.users.model.User;

import java.util.HashSet;


public class UserDetailsServiceImplTest extends BaseServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImplTest.class);

    UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserRepository userRepository;

    private User user;

    @Before
    public void setup() {
        this.user = new User("testUser", "testPassword", "test@email.com");
        this.user.setRoles(new HashSet<>());
        this.userDetailsService = new UserDetailsServiceImpl(this.userRepository);
    }

    @Test
    public void test_userRepositoryCreateUser() {
        User createdUser = this.userRepository.saveAndFlush(this.user);
        User checkUser = this.userRepository.getOne(createdUser.getId());
        Assert.assertEquals(createdUser.getId(), checkUser.getId());
        Assert.assertEquals(createdUser.getUsername(), checkUser.getUsername());
        Assert.assertEquals(createdUser.getPassword(), checkUser.getPassword());
        Assert.assertEquals(createdUser.getEmail(), checkUser.getEmail());
        Assert.assertEquals(createdUser.getRoles(), checkUser.getRoles());
    }

}
