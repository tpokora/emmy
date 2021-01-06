package org.tpokora.application.weather.views;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.tpokora.application.common.views.BaseViewControllerWebTest;
import org.tpokora.persistance.services.weather.ForecastDaoService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {WeatherDataViewController.class} )
class WeatherDataViewControllerWebTest extends BaseViewControllerWebTest {

    @Autowired
    WeatherDataViewController weatherDataViewController;

    @MockBean
    private ForecastDaoService forecastDaoService;

    @Test
    void contextLoads() throws Exception {
        assertThat(weatherDataViewController).isNotNull();
    }
}