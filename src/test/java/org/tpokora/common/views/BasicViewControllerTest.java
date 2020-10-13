package org.tpokora.common.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.tpokora.TestApplication;
import org.tpokora.config.TestH2DatabaseConfiguration;
import org.tpokora.config.security.SecurityConfiguration;
import org.tpokora.users.dao.RolesRepository;
import org.tpokora.users.dao.UserRepository;
import org.tpokora.persistance.entity.users.User;


@SpringBootTest(classes = {TestApplication.class, TestH2DatabaseConfiguration.class, SecurityConfiguration.class})
@AutoConfigureMockMvc
@TestPropertySource(locations = { "classpath:application-test.properties", "classpath:application-db-test.properties"})
abstract public class BasicViewControllerTest {

    public static final String TEST_USER = "testUser";
    public static final User USER = new User(TEST_USER, "tesetPassword", "test@email.com");
    public static final String TEST_ROLE = "TEST_ROLE";

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RolesRepository rolesRepository;
}
