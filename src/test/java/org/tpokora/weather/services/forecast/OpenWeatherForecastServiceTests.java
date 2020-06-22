package org.tpokora.weather.services.forecast;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.tpokora.common.utils.FileReaderUtils;
import org.tpokora.weather.model.Coordinates;
import org.tpokora.weather.model.Forecast;
import org.tpokora.weather.properties.OpenWeatherProperties;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class OpenWeatherForecastServiceTests {

    public static final double LONGITUDE = 11.11;
    public static final double LATITUDE = 22.22;

    @Mock
    private OpenWeatherProperties openWeatherProperties;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ForecastService forecastService;

    @BeforeEach
    public void setup()
    {
        Mockito.when(openWeatherProperties.getValue(OpenWeatherProperties.HOST)).thenReturn("testHost");
        Mockito.when(openWeatherProperties.getValue(OpenWeatherProperties.KEY)).thenReturn("testKey");
        Mockito.when(openWeatherProperties.getValue(OpenWeatherProperties.ID)).thenReturn("123");
    }

    @Test
    public void testGetForecast() {
        String fileToString = FileReaderUtils.fileToString("weather/forecast/openweatherResponse.json");
        ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(fileToString, null, HttpStatus.OK);
        Mockito.when(restTemplate.exchange(
                anyString(), any(HttpMethod.class), any(HttpEntity.class), (Class<String>) any(), anyMap())
        ).thenReturn(stringResponseEntity);
        Optional<Forecast> forecast = forecastService.getForecast(LONGITUDE, LATITUDE);
        Assertions.assertTrue(forecast.isPresent());

        Optional<Forecast> forecastByCoordinates = forecastService.getForecast(new Coordinates(LONGITUDE, LATITUDE));
        Assertions.assertTrue(forecastByCoordinates.isPresent());
    }

    @Test
    public void testGetForecast_fail() {
        String fileToString = FileReaderUtils.fileToString("weather/forecast/openweatherResponse.json");
        ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(fileToString, null, HttpStatus.BAD_REQUEST);
        Mockito.when(restTemplate.exchange(
                anyString(), any(HttpMethod.class), any(HttpEntity.class), (Class<String>) any(), anyMap())
        ).thenReturn(stringResponseEntity);
        Optional<Forecast> forecast = forecastService.getForecast(LONGITUDE, LATITUDE);
        Assertions.assertTrue(forecast.isEmpty());
    }
}
