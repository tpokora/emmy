package org.tpokora.config.constants;

public class WeatherViewConstants {

    public static final String WEATHER_VIEW = "weather";
    public static final String WEATHER_VIEW_TEMPLATE = "weather/weather";
    public static final String WEATHER_DATA_VIEW_TEMPLATE = "weather/weather_data";
    public static final String WEATHER_VIEW_URL = CommonConstants.SLASH + WEATHER_VIEW;
    public static final String WEATHER_DATA_VIEW_URL = CommonConstants.SLASH + WEATHER_VIEW + "-data";

    public static final String WEATHER_FIND_LOCATION_URL = WEATHER_VIEW_URL + "/find-location";
    public static final String WEATHER_FIND_STORM_URL = WEATHER_VIEW_URL + "/find-storm";
    public static final String WEATHER_FIND_WARNINGS_URL = WEATHER_VIEW_URL + "/find-warnings";
    public static final String WEATHER_FIND_FORECAST_URL = WEATHER_VIEW_URL + "/find-forecast";

    /**
     * Class is providing static constant strings for WeatherViewController
     */
    private WeatherViewConstants() {}
}
