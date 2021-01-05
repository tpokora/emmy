package org.tpokora.persistance.entity.weather;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.tpokora.common.utils.DateUtils;

import java.time.LocalDateTime;

public class ForecastEntityTest {

    @Test
    public void testForecastEntityToString() {
        int id = 1;
        String location = "location";
        String name = "name";
        String description = "description";
        double temp = 1.1;
        double feelTemp = 1.2;
        double maxTemp = 5.0;
        double minTemp = 1.0;
        int pressure = 900;
        int humidity = 60;
        double wind = 3.5;
        double rain1h = 0.2;
        double rain3h = 2.0;
        double longitude = 11.11;
        double latitude = 22.22;
        LocalDateTime timestamp = LocalDateTime.now();

        ForecastEntity forecastEntity = ForecastEntity.builder()
                .id(id)
                .location(location)
                .name(name)
                .description(description)
                .temp(temp)
                .feelTemp(feelTemp)
                .maxTemp(maxTemp)
                .minTemp(minTemp)
                .pressure(pressure)
                .humidity(humidity)
                .wind(wind)
                .rain1h(rain1h)
                .rain3h(rain3h)
                .longitude(longitude)
                .latitude(latitude)
                .timestamp(timestamp)
                .build();

        String expectedString = String.format("ForecastEntity{id=%s, location='%s', name='%s', description='%s', temp=%s, feelTemp=%s, minTemp=%s, maxTemp=%s, pressure=%s, humidity=%s, wind=%s, rain1h=%s, rain3h=%s, longitude=%s, latitude=%s, timestamp=%s}",
                forecastEntity.getId(), forecastEntity.getLocation(), forecastEntity.getName(), forecastEntity.getDescription(),
                forecastEntity.getTemp(), forecastEntity.getFeelTemp(), forecastEntity.getMinTemp(), forecastEntity.getMaxTemp(),
                forecastEntity.getPressure(), forecastEntity.getHumidity(), forecastEntity.getWind(), forecastEntity.getRain1h(),
                forecastEntity.getRain3h(), forecastEntity.getLongitude(), forecastEntity.getLatitude(),
                DateUtils.parseDateToString(forecastEntity.getTimestamp()));

        Assertions.assertEquals(expectedString, forecastEntity.toString());
    }
}
