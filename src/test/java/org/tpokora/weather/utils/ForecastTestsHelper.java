package org.tpokora.weather.utils;

import org.tpokora.common.utils.DateUtils;
import org.tpokora.weather.model.Forecast;

public class ForecastTestsHelper {

    public static Forecast createForecast() {
        Forecast forecast = new Forecast();

        forecast.setId(0);
        forecast.setName("testName");
        forecast.setDescription("testDescription");
        forecast.setTemp(1.1);
        forecast.setFeelTemp(1.2);
        forecast.setMinTemp(0.9);
        forecast.setMaxTemp(2.9);
        forecast.setPressure(1000);
        forecast.setHumidity(10);
        forecast.setWind(10.1);
        forecast.setRain1h(0.27);
        forecast.setRain3h(1.27);
        forecast.setLongitude(11.11);
        forecast.setLatitude(22.11);
        forecast.setTimestamp(DateUtils.getCurrentLocalDateTime());
        return forecast;
    }
}
