package org.tpokora.users.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.tpokora.Application;
import org.tpokora.users.model.User;

import javax.annotation.Resource;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@WebAppConfiguration
public class UserRepositoryTest {

    public static final String USERNAME = "newUser";

    @Resource
    private UserRepository userRepository;

    @Test
    public void test_getUserByUsername_success() {
        User createUser = new User(USERNAME, "", "testUser@email.com");
        userRepository.save(createUser);

        User studentFromDB = userRepository.findByUsername("newUser");
        Assert.assertEquals(createUser.getUsername(), studentFromDB.getUsername());
        Assert.assertEquals(createUser.getPassword(), studentFromDB.getPassword());
        Assert.assertEquals(createUser.getEmail(), studentFromDB.getEmail());
    }
}
