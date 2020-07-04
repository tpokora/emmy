package org.tpokora.config.security;

import static org.tpokora.auth.AuthConstatns.SIGNIN_VIEW_URL;
import static org.tpokora.config.security.SecurityConfiguration.HOME;
import static org.tpokora.console.web.ConsoleViewConstants.CONSOLE_VIEW_URL;
import static org.tpokora.weather.views.WeatherViewConstants.WEATHER_VIEW_URL;

public class SecurityMatchers {

    private SecurityMatchers() {};

    public static final String ALL_MATCHER_SUFFIX = "/**";
    public static final String API_MATCHER = "/api" + ALL_MATCHER_SUFFIX;
    public static final String WEATHER_VIEW_MATCHER = WEATHER_VIEW_URL + ALL_MATCHER_SUFFIX;
    public static final String CONSOLE_VIEW_MATCHER = CONSOLE_VIEW_URL + ALL_MATCHER_SUFFIX;

    public final static String[] STATIC_FILES_MATCHERS = {"/**/*.js", "/**/*.css" };
    public final static String[] ALL_ACCESS_MATCHERS = { "/", HOME, WEATHER_VIEW_MATCHER, SIGNIN_VIEW_URL, "/add-user", API_MATCHER};
    public final static String[] ADMIN_ONLY_MATCHERS = {CONSOLE_VIEW_MATCHER};
}
