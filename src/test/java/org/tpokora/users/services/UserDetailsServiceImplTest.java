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

import java.util.List;


public class UserDetailsServiceImplTest extends BaseServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImplTest.class);

    UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserRepository userRepository;

    @Before
    public void setup() {
        this.userDetailsService = new UserDetailsServiceImpl(this.userRepository);
    }

    @Test
    public void test_userRepositoryFindAll() {
        List<User> allUsers = this.userRepository.findAll();
        Assert.assertEquals(1, allUsers.size());
    }
}
