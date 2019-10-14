package org.tpokora.views.weather;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.tpokora.config.properties.NotificationProperties;
import org.tpokora.storms.services.FindCityService;
import org.tpokora.storms.services.FindStormService;
import org.tpokora.storms.services.FindWarningService;
import org.tpokora.views.AbstractView;
import org.tpokora.views.MainView;
import org.tpokora.views.common.RouteStrings;

@Tag("weather-view")
@Route(value = RouteStrings.WEATHER_ROUTE, layout = MainView.class)
@PageTitle(RouteStrings.WEATHER)
public class WeatherView extends AbstractView {

    private FindCityForm findCityForm;
    private FindStormForm findStormForm;
    private FindWarningsForm findWarningsForm;
    private WeatherService weatherService;

    public WeatherView(FindCityService findCityService, FindStormService findStormService,
                       FindWarningService findWarningService, WeatherService weatherService) {
        setupContentDefaultStyles();
        addToContent(new H3(RouteStrings.WEATHER));
        this.weatherService = weatherService;
        this.findCityForm = new FindCityForm(this.weatherService, findCityService);
        this.findStormForm = new FindStormForm(this.weatherService, findStormService);
        this.findWarningsForm = new FindWarningsForm(this.weatherService, findWarningService);
        Details findCityElement = new Details("Find City",
                findCityForm);
        Details findStormElement = new Details("Find Storm",
                findStormForm);
        Details findWarningsElement = new Details("Find Warnings",
                findWarningsForm);
        findStormElement.addOpenedChangeListener(e -> {
            this.findStormForm.refreshInputs();
            if (e.isOpened()) {
            }
        });
        findWarningsElement.addOpenedChangeListener(e -> {
            this.findWarningsForm.refreshInputs();
            if (e.isOpened()) {
            }
        });


        addToContent(findCityElement, findStormElement, findWarningsElement);
    }
}
