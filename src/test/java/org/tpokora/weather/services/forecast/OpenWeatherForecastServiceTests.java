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
import org.tpokora.common.utils.DateUtils;
import org.tpokora.common.utils.FileReaderUtils;
import org.tpokora.persistance.entity.weather.ForecastEntity;
import org.tpokora.weather.dao.ForecastDaoService;
import org.tpokora.domain.weather.Coordinates;
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

    @Mock
    private ForecastDaoService forecastDaoService;

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
        Mockito.when(forecastDaoService.saveForecast(any())).thenReturn(createForecast());
        Optional<ForecastEntity> forecast = forecastService.getForecast(LONGITUDE, LATITUDE);
        Assertions.assertTrue(forecast.isPresent());

        Optional<ForecastEntity> forecastByCoordinates = forecastService.getForecast(new Coordinates(LONGITUDE, LATITUDE));
        Assertions.assertTrue(forecastByCoordinates.isPresent());
    }

    @Test
    public void testGetForecast_fail() {
        String fileToString = FileReaderUtils.fileToString("weather/forecast/openweatherResponse.json");
        ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(fileToString, null, HttpStatus.BAD_REQUEST);
        Mockito.when(restTemplate.exchange(
                anyString(), any(HttpMethod.class), any(HttpEntity.class), (Class<String>) any(), anyMap())
        ).thenReturn(stringResponseEntity);
        Optional<ForecastEntity> forecast = forecastService.getForecast(LONGITUDE, LATITUDE);
        Assertions.assertTrue(forecast.isEmpty());
    }

    private ForecastEntity createForecast() {
        ForecastEntity forecastEntity = new ForecastEntity();

        forecastEntity.setId(1);
        forecastEntity.setName("testName");
        forecastEntity.setName("testDescription");
        forecastEntity.setTemp(1.1);
        forecastEntity.setFeelTemp(1.2);
        forecastEntity.setMinTemp(0.9);
        forecastEntity.setMaxTemp(2.9);
        forecastEntity.setPressure(1000);
        forecastEntity.setHumidity(10);
        forecastEntity.setWind(10.1);
        forecastEntity.setLongitude(11.11);
        forecastEntity.setLatitude(22.11);
        forecastEntity.setTimestamp(DateUtils.getCurrentLocalDateTime());
        return forecastEntity;
    }
}

