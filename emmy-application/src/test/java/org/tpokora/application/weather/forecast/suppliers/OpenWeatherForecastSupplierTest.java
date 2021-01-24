package org.tpokora.application.weather.forecast.suppliers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.tpokora.application.common.utils.FileReaderUtils;
import org.tpokora.application.weather.mapper.OpenWeatherForecastMapper;
import org.tpokora.application.weather.properties.OpenWeatherProperties;
import org.tpokora.persistance.entity.weather.ForecastEntity;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class OpenWeatherForecastSupplierTest {

    public static final double LONGITUDE = 11.11;
    public static final double LATITUDE = 22.22;

    @Mock
    private OpenWeatherProperties openWeatherProperties;

    @Mock
    private RestTemplate restTemplate;

    private OpenWeatherForecastMapper openWeatherForecastMapper;

    @InjectMocks
    private OpenWeatherForecastSupplier openWeatherForecastSupplier;

    @BeforeEach
    void setup() {
        Mockito.when(openWeatherProperties.getValue(OpenWeatherProperties.HOST)).thenReturn("testHost");
        Mockito.when(openWeatherProperties.getValue(OpenWeatherProperties.KEY)).thenReturn("testKey");
        Mockito.when(openWeatherProperties.getValue(OpenWeatherProperties.ID)).thenReturn("123");
        openWeatherForecastMapper = new OpenWeatherForecastMapper();
        openWeatherForecastSupplier = new OpenWeatherForecastSupplier(restTemplate, openWeatherForecastMapper, openWeatherProperties);
    }

    @Test
    void testGetForecast() {
        String fileToString = FileReaderUtils.fileToString("weather/forecast/openweatherResponse.json");
        ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(fileToString, null, HttpStatus.OK);
        Mockito.when(restTemplate.exchange(
                ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(HttpEntity.class), (Class<String>) ArgumentMatchers.any(), ArgumentMatchers.anyMap())
        ).thenReturn(stringResponseEntity);

        Optional<ForecastEntity> forecastOptional = openWeatherForecastSupplier.getForecast(LONGITUDE, LATITUDE);

        Assertions.assertTrue(forecastOptional.isPresent());

        ForecastEntity forecastEntity = forecastOptional.get();
        Assertions.assertEquals("Vakaga Prefecture", forecastEntity.getLocation());
        Assertions.assertEquals("Clear", forecastEntity.getName());
        Assertions.assertEquals("clear sky", forecastEntity.getDescription());
        Assertions.assertEquals(28.55, forecastEntity.getTemp());
        Assertions.assertEquals(29.04, forecastEntity.getFeelTemp());
        Assertions.assertEquals(28.55, forecastEntity.getMinTemp());
        Assertions.assertEquals(28.55, forecastEntity.getMaxTemp());
        Assertions.assertEquals(1012, forecastEntity.getPressure());
        Assertions.assertEquals(56, forecastEntity.getHumidity());
        Assertions.assertEquals(3.85, forecastEntity.getWind());
        Assertions.assertEquals(0.27, forecastEntity.getRain1h());
        Assertions.assertEquals(1.2, forecastEntity.getRain3h());
        Assertions.assertEquals(22.22, forecastEntity.getLongitude());
        Assertions.assertEquals(11.11, forecastEntity.getLatitude());
    }

    @Test
    void testGetForecast_fail() {
        String fileToString = FileReaderUtils.fileToString("weather/forecast/openweatherResponse.json");
        ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(fileToString, null, HttpStatus.BAD_REQUEST);
        Mockito.when(restTemplate.exchange(
                ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class), ArgumentMatchers.any(HttpEntity.class), (Class<String>) ArgumentMatchers.any(), ArgumentMatchers.anyMap())
        ).thenReturn(stringResponseEntity);
        Optional<ForecastEntity> forecastOptional = openWeatherForecastSupplier.getForecast(LONGITUDE, LATITUDE);

        Assertions.assertTrue(forecastOptional.isEmpty());
    }
}