package org.tpokora.application.weather.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.tpokora.persistance.entity.weather.ForecastEntity;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(WeatherAPIController.class)
@TestPropertySource(locations = {"classpath:application-test.properties", "classpath:application-db-test.properties"})
@EnableAutoConfiguration(exclude = JpaRepositoriesAutoConfiguration.class)
public class WeatherAPIControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherAPIController weatherAPIController;

    @Test
    public void testGetForecastsByCoordinatesFromPeriod() throws Exception {
        ForecastEntity forecastEntity = new ForecastEntity();
        forecastEntity.setLocation("Test Location");

        List<ForecastEntity> forecastEntityList = Collections.singletonList(forecastEntity);
        ResponseEntity<List<ForecastEntity>> responseEntity = new ResponseEntity<>(forecastEntityList, HttpStatus.OK);

        given(weatherAPIController.getForecastsByCoordinatesFromPeriod(anyDouble(), anyDouble(), any(), any())).willReturn(responseEntity);

        String URL_GET_ARCHIVE_FORECASTS_BY_LOCATION = "/api/weather/getArchiveForecastsFromDate?longitude=11&latitude=22&startDate=2020-08-01&endDate=2020-08-01";
        mockMvc.perform(get(URL_GET_ARCHIVE_FORECASTS_BY_LOCATION)
                .with(user("testUser").password("testPassword"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].location", is(forecastEntity.getLocation())));

    }

    @Test
    public void testGetArchiveForecastsByLocation() throws Exception {
        ForecastEntity forecastEntity = new ForecastEntity();
        forecastEntity.setLocation("Test Location");

        List<ForecastEntity> forecastEntityList = Collections.singletonList(forecastEntity);
        ResponseEntity<List<ForecastEntity>> responseEntity = new ResponseEntity<>(forecastEntityList, HttpStatus.OK);

        given(weatherAPIController.getForecastsFromPeriodByLocation(anyString(), any(), any())).willReturn(responseEntity);

        String URL_GET_ARCHIVE_FORECASTS_BY_LOCATION = "/api/weather/getArchiveForecastsByLocation?location=Skawina&startDate=2020-08-01&endDate=2020-08-01";
        mockMvc.perform(get(URL_GET_ARCHIVE_FORECASTS_BY_LOCATION)
                .with(user("testUser").password("testPassword"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].location", is(forecastEntity.getLocation())));

    }
}
