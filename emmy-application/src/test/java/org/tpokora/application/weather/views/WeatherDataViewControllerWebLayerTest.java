package org.tpokora.application.weather.views;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.tpokora.application.common.views.BaseViewControllerWebLayerTest;
import org.tpokora.common.utils.DateUtils;
import org.tpokora.persistance.entity.weather.ForecastEntity;
import org.tpokora.persistance.services.weather.ForecastDaoService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(WeatherDataViewController.class)
class WeatherDataViewControllerWebLayerTest extends BaseViewControllerWebLayerTest {

    public static final String WEATHER_DATA_URL = "/weather-data";
    public static final String WEATHER_DATA_FIND_BY_URL = "/weather-data/find";

    public static final String WEATHER_DATA_MESSAGE = "<h1>Weather Data</h1>";
    public static final String WEATHER_DATA_FIND_HEADER = "<h3>Find Weather Data by Location name</h3>";

    @MockBean
    private ForecastDaoService forecastDaoService;

    @Test
    @WithMockUser
    void testWeatherDataView() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get(WEATHER_DATA_URL)).andDo(print()).andExpect(status().isOk()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        assertBasicContent(responseBody);
    }

    @Test
    @WithMockUser
    void testWeatherDataViewFindByLocationName_noForecastsFound() throws Exception {
        String location = "testCity";
        Mockito.when(forecastDaoService.findAllByLocationBetweenDates(anyString(), any(), any())).thenReturn(Collections.emptyList());
        MvcResult mvcResult = this.mockMvc.perform(get(WEATHER_DATA_FIND_BY_URL + "?name=" + location)).andDo(print()).andExpect(status().isOk()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        assertBasicContent(responseBody);

        String noForecastFound = "<h2>No Forecast found</h2>";
        Assertions.assertTrue(responseBody.contains(noForecastFound));
    }

    @Test
    @WithMockUser
    void testWeatherDataViewFindByLocationName_twoForecasts() throws Exception {
        String location = "testCity";
        List<ForecastEntity> forecastEntities = forecastListMock();
        Mockito.when(forecastDaoService.findAllByLocationBetweenDates(anyString(), any(), any())).thenReturn(forecastEntities);
        MvcResult mvcResult = this.mockMvc.perform(get(WEATHER_DATA_FIND_BY_URL + "?name=" + location)).andDo(print()).andExpect(status().isOk()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        assertBasicContent(responseBody);
        forecastEntities.forEach(forecastEntity -> {
            String locationElement = tableElementString(forecastEntity.getLocation());
            String timestampElement = tableElementString(DateUtils.parseDateToString(forecastEntity.getTimestamp()));

            Assertions.assertTrue(responseBody.contains(locationElement));
            Assertions.assertTrue(responseBody.contains(timestampElement));
        });
    }

    private void assertBasicContent(String body) {
        Assertions.assertTrue(body.contains(WEATHER_DATA_MESSAGE));
        Assertions.assertTrue(body.contains(WEATHER_DATA_FIND_HEADER));
    }

    private List<ForecastEntity> forecastListMock() {
        List<ForecastEntity> forecasts = new ArrayList();
        ForecastEntity forecastOne = ForecastEntity.builder()
                .id(1)
                .name("Clouds")
                .location("testCity")
                .description("testDescription")
                .timestamp(LocalDateTime.now().minusDays(1))
                .build();

        ForecastEntity forecastTwo = ForecastEntity.builder()
                .id(2)
                .name("Rainy")
                .location("testCity")
                .description("testDescription")
                .timestamp(LocalDateTime.now().minusDays(2))
                .build();
        forecasts.add(forecastOne);
        forecasts.add(forecastTwo);
        return forecasts;
    }

    private String tableElementString(String value) {
        return String.format("<td>%s</td>", value);
    }
}