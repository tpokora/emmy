package org.tpokora.views.weather;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.tpokora.storms.model.City;
import org.tpokora.storms.services.FindCityService;
import org.tpokora.storms.services.FindStormService;
import org.tpokora.views.AbstractView;
import org.tpokora.views.MainView;
import org.tpokora.views.common.RouteStrings;

@Tag("weather-view")
@Route(value = RouteStrings.WEATHER_ROUTE, layout = MainView.class)
@PageTitle(RouteStrings.WEATHER)
public class WeatherView extends AbstractView {

    private FindCityForm findCityForm;
    private FindStormForm findStormForm;
    private City city;

    public WeatherView(FindCityService findCityService, FindStormService findStormService) {
        setupContentDefaultStyles();
        addToContent(new H3(RouteStrings.WEATHER));
        this.city = new City();
        this.findCityForm = new FindCityForm(this.city, findCityService);
        this.findStormForm = new FindStormForm(this.city.getCoordinates(), findStormService);
        Details findCityElement = new Details("Find City",
                findCityForm);
        Details findStormElement = new Details("Find Storm",
                findStormForm);
        findStormElement.addOpenedChangeListener(e -> {

            Notification.show(e.isOpened() ? this.city.toString() : "Y: " + this.city.toString());
        });

        addToContent(findCityElement, findStormElement);
    }
}
