package org.tpokora.application.weather.services.location;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.tpokora.application.common.utils.FileReaderUtils;
import org.tpokora.domain.weather.Location;
import org.tpokora.application.weather.location.OpenCageDataLocationService;
import org.tpokora.application.weather.properties.OpenCageDataProperties;
import org.tpokora.application.weather.properties.OpenWeatherProperties;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OpenCageDataLocationServiceTest {

    private static final String WEATHER_LOCATION_OPENCAGE_DATA_RESPONSE_JSON = "weather/location/opencageDataResponse.json";

    @Mock
    private OpenCageDataProperties openCageDataProperties;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private OpenCageDataLocationService openCageDataLocationService;

    @BeforeEach
    public void setup() {
        Mockito.when(openCageDataProperties.getValue(OpenWeatherProperties.KEY)).thenReturn("testKey");
    }

    @Test
    void testGetCityCoordinatesByName() {
        String fileToString = FileReaderUtils.fileToString(WEATHER_LOCATION_OPENCAGE_DATA_RESPONSE_JSON);
        ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(fileToString, null, HttpStatus.OK);
        Mockito.when(restTemplate.exchange(
                ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class), ArgumentMatchers.any(HttpEntity.class), (Class<String>) ArgumentMatchers.any(), ArgumentMatchers.anyMap())
        ).thenReturn(stringResponseEntity);
        Optional<Location> cityCoordinatesByName = openCageDataLocationService.getLocationCoordinatesByName("Warszawa");
        Assertions.assertTrue(cityCoordinatesByName.isPresent());
    }

    @Test
    public void testGetForecast_fail() {
        String fileToString = FileReaderUtils.fileToString(WEATHER_LOCATION_OPENCAGE_DATA_RESPONSE_JSON);
        ResponseEntity<String> stringResponseEntity = new ResponseEntity<>(fileToString, null, HttpStatus.BAD_REQUEST);
        Mockito.when(restTemplate.exchange(
                ArgumentMatchers.anyString(), ArgumentMatchers.any(HttpMethod.class), ArgumentMatchers.any(HttpEntity.class), (Class<String>) ArgumentMatchers.any(), ArgumentMatchers.anyMap())
        ).thenReturn(stringResponseEntity);
        Optional<Location> city = openCageDataLocationService.getLocationCoordinatesByName("Warszawa");
        Assertions.assertTrue(city.isEmpty());
    }
}