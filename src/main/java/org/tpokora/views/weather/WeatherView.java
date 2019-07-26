package org.tpokora.views.weather;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.tpokora.storms.model.City;
import org.tpokora.storms.services.FindCityService;
import org.tpokora.views.AbstractView;
import org.tpokora.views.MainView;
import org.tpokora.views.common.RouteStrings;

@Tag("weather-view")
@Route(value = RouteStrings.WEATHER_ROUTE, layout = MainView.class)
@PageTitle(RouteStrings.WEATHER)
public class WeatherView extends AbstractView {

    private FindCityForm findCityForm;
    private City city;

    public WeatherView(FindCityService findCityService) {
        setupContentDefaultStyles();
        addToContent(new H3(RouteStrings.WEATHER));
        this.city = new City();
        findCityForm = new FindCityForm(city, findCityService);
        Details findCityElement = new Details("Find City",
                findCityForm);
        addToContent(findCityElement);
    }
}
