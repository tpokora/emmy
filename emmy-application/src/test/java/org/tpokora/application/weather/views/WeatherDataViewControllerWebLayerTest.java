package org.tpokora.application.weather.views;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.tpokora.application.common.views.BaseViewControllerWebLayerTest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(WeatherDataViewController.class)
class WeatherDataViewControllerWebLayerTest extends BaseViewControllerWebLayerTest {

    public static final String WEATHER_DATA_URL = "/weather-data";
    public static final String WEATHER_DATA_MESSAGE = "Weather Data";

    @Test
    @WithMockUser
    void testWeatherDataView() throws Exception {
        this.mockMvc.perform(get(WEATHER_DATA_URL)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(WEATHER_DATA_MESSAGE)));
    }
}