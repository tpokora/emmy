package org.tpokora.weather.views;

import static org.tpokora.common.CommonConstants.SLASH;

public class WeatherViewConstants {

    public static final String WEATHER_VIEW = "weather";
    public static final String WEATHER_VIEW_TEMPLATE = "weather/weather";
    public static final String WEATHER_VIEW_URL = SLASH + WEATHER_VIEW;

    public static final String WEATHER_FIND_LOCATION_URL = WEATHER_VIEW_URL + "/find-location";
    public static final String WEATHER_FIND_STORM_URL = WEATHER_VIEW_URL + "/find-storm";
    public static final String WEATHER_FIND_WARNINGS_URL = WEATHER_VIEW_URL + "/find-warnings";
    public static final String WEATHER_FIND_FORECAST_URL = WEATHER_VIEW_URL + "/find-forecast";

    /**
     * Class is providing static constant strings for WeatherViewController
     */
    private WeatherViewConstants() {}
}
