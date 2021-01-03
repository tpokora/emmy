package org.tpokora.application.weather.views;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.tpokora.application.common.views.BaseViewControllerWebTest;
import org.tpokora.application.weather.forecast.ForecastService;
import org.tpokora.application.weather.location.OpenCageDataLocationService;
import org.tpokora.application.weather.storms.FindCityService;
import org.tpokora.application.weather.storms.FindStormService;
import org.tpokora.application.weather.storms.FindWarningService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {WeatherViewController.class} )
class WeatherViewControllerWebTest extends BaseViewControllerWebTest {

    @Autowired
    WeatherViewController weatherViewController;

    @MockBean
    OpenCageDataLocationService openCageDataLocationService;
    @MockBean
    FindCityService findCityService;
    @MockBean
    FindStormService findStormService;
    @MockBean
    FindWarningService findWarningService;
    @MockBean
    ForecastService forecastService;

    @Test
    void contextLoads() throws Exception {
        assertThat(weatherViewController).isNotNull();
    }
}