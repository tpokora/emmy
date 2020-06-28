package org.tpokora.weather.utils;

import org.tpokora.common.utils.DateUtils;
import org.tpokora.weather.model.ForecastEntity;

public class ForecastTestsHelper {

    public static ForecastEntity createForecast() {
        ForecastEntity forecastEntity = new ForecastEntity();

        forecastEntity.setId(0);
        forecastEntity.setName("testName");
        forecastEntity.setDescription("testDescription");
        forecastEntity.setTemp(1.1);
        forecastEntity.setFeelTemp(1.2);
        forecastEntity.setMinTemp(0.9);
        forecastEntity.setMaxTemp(2.9);
        forecastEntity.setPressure(1000);
        forecastEntity.setHumidity(10);
        forecastEntity.setWind(10.1);
        forecastEntity.setRain1h(0.27);
        forecastEntity.setRain3h(1.27);
        forecastEntity.setLongitude(11.11);
        forecastEntity.setLatitude(22.11);
        forecastEntity.setTimestamp(DateUtils.getCurrentLocalDateTime());
        return forecastEntity;
    }
}
