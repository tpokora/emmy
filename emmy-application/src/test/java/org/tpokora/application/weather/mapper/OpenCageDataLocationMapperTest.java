package org.tpokora.application.weather.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.tpokora.application.common.utils.FileReaderUtils;
import org.tpokora.domain.weather.Location;

class OpenCageDataLocationMapperTest {

    private final String WEATHER_LOCATION_OPEN_CAGE_DATA_RESPONSE_CITY_JSON = "weather/location/openCageDataResponse_city.json";
    private final String WEATHER_LOCATION_OPEN_CAGE_DATA_RESPONSE_TOWN_JSON = "weather/location/openCageDataResponse_town.json";
    private final String WEATHER_LOCATION_OPEN_CAGE_DATA_RESPONSE_VILLAGE_JSON = "weather/location/openCageDataResponse_village.json";
    private final String WEATHER_LOCATION_OPEN_CAGE_DATA_RESPONSE_EMPTY_RESULTS_JSON = "weather/location/openCageDataResponse_emptyResults.json";
    private OpenCageDataLocationMapper openCageDataLocationMapper;
    private String openCageDataLocationStringResponse;

    @BeforeEach
    public void setup() {
        openCageDataLocationMapper = new OpenCageDataLocationMapper();
    }

    @Test
    void testMap_city() {
        openCageDataLocationStringResponse = FileReaderUtils.fileToString(WEATHER_LOCATION_OPEN_CAGE_DATA_RESPONSE_CITY_JSON);
        Location expectedLocation = new Location(19.9368564, 50.0619474);
        expectedLocation.setName("Krakow");
        Location location = openCageDataLocationMapper.map(openCageDataLocationStringResponse);
        Assertions.assertEquals(expectedLocation.toString(), location.toString());
    }

    @Test
    void testMap_town() {
        openCageDataLocationStringResponse = FileReaderUtils.fileToString(WEATHER_LOCATION_OPEN_CAGE_DATA_RESPONSE_TOWN_JSON);
        Location expectedLocation = new Location(19.9368564, 50.0619474);
        expectedLocation.setName("Krakow");
        Location location = openCageDataLocationMapper.map(openCageDataLocationStringResponse);
        Assertions.assertEquals(expectedLocation.toString(), location.toString());
    }

    @Test
    void testMap_village() {
        openCageDataLocationStringResponse = FileReaderUtils.fileToString(WEATHER_LOCATION_OPEN_CAGE_DATA_RESPONSE_VILLAGE_JSON);
        Location expectedLocation = new Location(19.9368564, 50.0619474);
        expectedLocation.setName("Krakow");
        Location location = openCageDataLocationMapper.map(openCageDataLocationStringResponse);
        Assertions.assertEquals(expectedLocation.toString(), location.toString());
    }

    @Test
    public void testMap_emptyResults() {
        openCageDataLocationStringResponse = FileReaderUtils.fileToString(WEATHER_LOCATION_OPEN_CAGE_DATA_RESPONSE_EMPTY_RESULTS_JSON);
        Location location = openCageDataLocationMapper.map(openCageDataLocationStringResponse);
        Assertions.assertNull(location);
    }

    @Test
    public void testMap_failureParsingJson() {
        String invalidJson = "invalidJson";
        Location location = openCageDataLocationMapper.map(invalidJson);
        Assertions.assertNull(location);
    }
}