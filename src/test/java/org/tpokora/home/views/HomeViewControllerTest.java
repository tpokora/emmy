package org.tpokora.home.views;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.tpokora.common.views.BasicViewControllerTest;


class HomeViewControllerTest extends BasicViewControllerTest {


    public static final String HOME_MESSAGE = "Welcome to Emmy App!";
    public static final String HOME_URL = "http://localhost:8080/home";

    @Test
    public void testHome() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> stringResponseEntity = testRestTemplate.getForEntity(HOME_URL, String.class);
        Assertions.assertEquals(HttpStatus.OK, stringResponseEntity.getStatusCode());
        Assertions.assertEquals(HOME_MESSAGE, stringResponseEntity.getBody().contains(HOME_MESSAGE));
    }

}