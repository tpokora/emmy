package org.tpokora.storms.views;

import org.tpokora.home.views.HomeViewConstants;

public class WeatherViewConstants {

    public static final String WEATHER_VIEW = "weather";
    public static final String WEATHER_VIEW_URL = HomeViewConstants.ROOT_URL + WEATHER_VIEW;

    public static final String WEATHER_FIND_CITY_URL = WEATHER_VIEW_URL + "/find-city";
    public static final String WEATHER_FIND_STORM_URL = WEATHER_VIEW_URL + "/find-storm";

    /**
     * Class is providing static constant strings for WeatherViewController
     */
    private WeatherViewConstants() {}
}
