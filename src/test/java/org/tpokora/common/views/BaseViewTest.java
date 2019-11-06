package org.tpokora.common.views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.tpokora.Application;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DataJpaTest
@TestPropertySource(locations = { "classpath:application-test.properties", "classpath:application-db-test.properties"})
public class BaseViewTest {

    @Autowired
    protected TestEntityManager entityManagerFactory;
}
