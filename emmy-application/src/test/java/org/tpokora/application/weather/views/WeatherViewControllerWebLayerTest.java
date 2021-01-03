package org.tpokora.application.weather.views;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MvcResult;
import org.tpokora.application.common.views.BaseViewControllerWebLayerTest;
import org.tpokora.application.weather.forecast.ForecastService;
import org.tpokora.application.weather.location.OpenCageDataLocationService;
import org.tpokora.application.weather.storms.FindCityService;
import org.tpokora.application.weather.storms.FindStormService;
import org.tpokora.application.weather.storms.FindWarningService;
import org.tpokora.domain.weather.Coordinates;
import org.tpokora.domain.weather.Location;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherViewController.class)
public class WeatherViewControllerWebLayerTest extends BaseViewControllerWebLayerTest {

    public static final String WEATHER_URL = "/weather";
    public static final String WEATHER_MESSAGE = "<h1>Weather</h1>";

    @MockBean
    OpenCageDataLocationService openCageDataLocationService;
    @MockBean
    FindCityService findCityService;
    @MockBean
    FindStormService findStormService;
    @MockBean
    FindWarningService findWarningService;
    @MockBean
    ForecastService forecastService;

    @Test
    @WithMockUser
    void testWeatherView() throws Exception {
        this.mockMvc.perform(get(WEATHER_URL)).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(WEATHER_MESSAGE)));
    }

    @Test
    @WithMockUser
    void testWeatherViewFindCity() throws Exception {
        String testCity = "testCity";
        Location testLocation = new Location();
        testLocation.setName(testCity);
        testLocation.setCoordinates(new Coordinates(11.22, 22.11));
        Mockito.when(openCageDataLocationService.getLocationCoordinatesByName(testCity)).thenReturn(Optional.of(testLocation));
        MvcResult mvcResult = this.mockMvc.perform(get(WEATHER_URL + "/find-location?name=" + testCity)).andDo(print()).andExpect(status().isOk()).andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

        // Example field in Location section
        // <div class="row text-success">>> Longitude: <b class="value">11.22</b></div>
        String coordinatesLongitude = String.format("<div class=\"row text-success\">>> Longitude: <b class=\"value\">%s</b></div>", testLocation.getCoordinates().getLongitude());
        String coordinatesLatitude = String.format("<div class=\"row text-success\">>> Latitude: <b class=\"value\">%s</b></div>", testLocation.getCoordinates().getLatitude());

        // Elements field in Forecast section
        // <input type="text" class="form-control" id="forecastLon" name="longitude" value="11.22"/>
        // <input type="text" class="form-control" id="forecastLat" name="latitude" value="22.11"/>
        String forecastLon = String.format("<input type=\"text\" class=\"form-control\" id=\"forecastLon\" name=\"longitude\" value=\"%s\"/>", testLocation.getCoordinates().getLongitude());
        String forecastLat = String.format("<input type=\"text\" class=\"form-control\" id=\"forecastLat\" name=\"latitude\" value=\"%s\"/>", testLocation.getCoordinates().getLatitude());

        // Elements field in Storms section
        // <input type="text" class="form-control" id="stormLon" name="longitude" value="11.22"/>
        // <input type="text" class="form-control" id="stormLat" name="latitude" value="22.11"/>
        String stormLon = String.format("<input type=\"text\" class=\"form-control\" id=\"stormLon\" name=\"longitude\" value=\"%s\"/>", testLocation.getCoordinates().getLongitude());
        String stormLat = String.format("<input type=\"text\" class=\"form-control\" id=\"stormLat\" name=\"latitude\" value=\"%s\"/>", testLocation.getCoordinates().getLatitude());

        // Elements field in Warnings section
        // <input type="text" class="form-control" id="warningsLon" name="longitude" value="11.22"/>
        // <input type="text" class="form-control" id="warningsLat" name="latitude" value="22.11"/>
        String warningsLon = String.format("<input type=\"text\" class=\"form-control\" id=\"warningsLon\" name=\"longitude\" value=\"%s\"/>", testLocation.getCoordinates().getLongitude());
        String warningsLat = String.format("<input type=\"text\" class=\"form-control\" id=\"warningsLat\" name=\"latitude\" value=\"%s\"/>", testLocation.getCoordinates().getLatitude());

        Assertions.assertTrue(responseBody.contains(coordinatesLongitude), "Find Location longitude is wrong");
        Assertions.assertTrue(responseBody.contains(coordinatesLatitude), "Find Location latitude is wrong");
        Assertions.assertTrue(responseBody.contains(forecastLon), "Find Forecast longitude is wrong");
        Assertions.assertTrue(responseBody.contains(forecastLat), "Find Forecast latitude is wrong");
        Assertions.assertTrue(responseBody.contains(stormLon), "Find Storm longitude is wrong");
        Assertions.assertTrue(responseBody.contains(stormLat), "Find Storm latitude is wrong");
        Assertions.assertTrue(responseBody.contains(warningsLon), "Find Warnings longitude is wrong");
        Assertions.assertTrue(responseBody.contains(warningsLat), "Find Warnings latitude is wrong");
    }
}
