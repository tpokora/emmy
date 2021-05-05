package org.tpokora.config.security;

import org.tpokora.config.constants.ConsoleViewConstants;

import static org.tpokora.config.constants.AuthConstants.SIGNIN_VIEW_URL;

public class SecurityMatchers {

    private SecurityMatchers() { }

    public static final String ALL_MATCHER_SUFFIX = "/**";
    public static final String API_MATCHER = "/api" + ALL_MATCHER_SUFFIX;
    public static final String CONSOLE_VIEW_MATCHER = ConsoleViewConstants.CONSOLE_VIEW_URL + ALL_MATCHER_SUFFIX;

    public final static String[] STATIC_FILES_MATCHERS = {"/**/*.js", "/**/*.css"};
    public final static String[] ALL_ACCESS_MATCHERS = {"/", "/login", SIGNIN_VIEW_URL, "/add-user", API_MATCHER, "/api/location/**", "/api/weather/forecast/**"};
    public final static String[] SWAGGER_MATCHERS = {"/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui.html**",
            "/webjars/**",
            "favicon.ico"
    };
    public final static String[] ADMIN_ONLY_MATCHERS = {CONSOLE_VIEW_MATCHER};
}
