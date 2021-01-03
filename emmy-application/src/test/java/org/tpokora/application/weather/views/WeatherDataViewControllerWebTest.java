package org.tpokora.application.weather.views;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.tpokora.application.common.views.BaseViewControllerWebTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {WeatherDataViewController.class} )
class WeatherDataViewControllerWebTest extends BaseViewControllerWebTest {

    @Autowired
    WeatherDataViewController weatherDataViewController;

    @Test
    void contextLoads() throws Exception {
        assertThat(weatherDataViewController).isNotNull();
    }
}