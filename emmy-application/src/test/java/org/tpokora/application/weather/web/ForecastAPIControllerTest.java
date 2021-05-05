package org.tpokora.application.weather.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.tpokora.application.weather.common.ForecastTestsHelper;
import org.tpokora.application.weather.web.service.ForecastAPIService;
import org.tpokora.persistance.entity.weather.ForecastEntity;
import org.tpokora.persistance.services.weather.ForecastDaoService;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ForecastAPIController.class)
@EnableAutoConfiguration(exclude = JpaRepositoriesAutoConfiguration.class)
public class ForecastAPIControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ForecastDaoService forecastDaoService;

    @MockBean
    private ForecastAPIService forecastAPIService;

    @Test
    void testGetForecastByLocation_notFound() throws Exception {
        when(forecastAPIService.getByLocation(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/weather/forecast?location=notFoundLocation")
                .with(user("testUser").password("testPassword"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testGetForecastByLocation_found() throws Exception {
        String locationName = "testLocation";
        double longitude = 11.11;
        double latitude = 22.22;
        ForecastEntity mockForecastEntity = ForecastTestsHelper.createForecastEntity(locationName, longitude, latitude);

        when(forecastAPIService.getByLocation(anyString())).thenReturn(Optional.of(mockForecastEntity));

        mockMvc.perform(get("/api/weather/forecast?location=testLocation")
                .with(user("testUser").password("testPassword"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.location", is(mockForecastEntity.getLocation())))
                .andExpect(jsonPath("$.longitude", is(mockForecastEntity.getLongitude())))
                .andExpect(jsonPath("$.latitude", is(mockForecastEntity.getLatitude())));
    }

    @Test
    void getForecastByCoordinates_notFound() throws Exception {
        when(forecastAPIService.getByLocation(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/weather/forecast?longitude=0.0&latitude=0.0")
                .with(user("testUser").password("testPassword"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void getForecastByCoordinates() throws Exception {
        String locationName = "testLocation";
        double longitude = 11.11;
        double latitude = 22.22;
        ForecastEntity mockForecastEntity = ForecastTestsHelper.createForecastEntity(locationName, longitude, latitude);

        when(forecastAPIService.getByLocation(anyString())).thenReturn(Optional.of(mockForecastEntity));

        mockMvc.perform(get("/api/weather/forecast?longitude=11.11&latitude=22.22")
                .with(user("testUser").password("testPassword"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
