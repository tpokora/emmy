package org.tpokora.application.auth.views;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.tpokora.application.auth.services.AuthViewService;
import org.tpokora.application.common.views.BaseViewControllerWebTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {AuthViewController.class} )
public class AuthViewControllerWebTest extends BaseViewControllerWebTest {

    @Autowired
    AuthViewController authViewController;

    @MockBean
    private AuthViewService authViewService;

    @Test
    void contextLoads() throws Exception {
        assertThat(authViewController).isNotNull();
    }
}
