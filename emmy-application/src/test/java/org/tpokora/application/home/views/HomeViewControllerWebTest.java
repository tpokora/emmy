package org.tpokora.application.home.views;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.tpokora.application.common.views.BaseViewControllerWebTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = {HomeViewController.class} )
public class HomeViewControllerWebTest extends BaseViewControllerWebTest {

    @Autowired
    HomeViewController homeViewController;

    @Test
    void contextLoads() throws Exception {
        assertThat(homeViewController).isNotNull();
    }
}
