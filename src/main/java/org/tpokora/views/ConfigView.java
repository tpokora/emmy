package org.tpokora.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.tpokora.config.properties.AppProperties;
import org.tpokora.config.properties.FirebaseProperties;
import org.tpokora.config.model.Property;
import org.tpokora.config.properties.NotificationProperties;
import org.tpokora.views.common.RouteStrings;

import java.util.ArrayList;

@Tag("config-view")
@Route(value = RouteStrings.CONFIG_ROUTE, layout = MainView.class)
@PageTitle(RouteStrings.CONFIG)
public class ConfigView extends AbstractView {

    private FirebaseProperties firebaseProperties;
    private AppProperties appProperties;
    private NotificationProperties notificationProperties;

    VerticalLayout verticalLayout = new VerticalLayout();
    Grid<Property> grid;

    public ConfigView(FirebaseProperties firebaseProperties, AppProperties appProperties, NotificationProperties notificationProperties) {
        this.firebaseProperties = firebaseProperties;
        this.appProperties = appProperties;
        this.notificationProperties = notificationProperties;

        this.verticalLayout.add(new H3(RouteStrings.CONFIG));
        this.grid = new Grid<>(Property.class);
        grid.setColumns("property", "value");
        grid.setItems(getAllProperties());
        this.verticalLayout.add(grid);

        setupContentDefaultStyles();
        addToContent(this.verticalLayout);
    }

    private ArrayList<Property> getAllProperties() {
        ArrayList<Property> propertyArrayList = new ArrayList<>();
        propertyArrayList.addAll(getFirebaseProperties());
        propertyArrayList.addAll(getNotificationProperties());
        return propertyArrayList;
    }

    private ArrayList<Property> getFirebaseProperties() {
        ArrayList<Property> propertyArrayList = new ArrayList<>();
        propertyArrayList.add(new Property("serverKey", firebaseProperties.getServerKey()));
        propertyArrayList.add(new Property("clientToken", firebaseProperties.getClientToken()));

        return propertyArrayList;
    }

    private ArrayList<Property> getNotificationProperties() {
        ArrayList<Property> propertyArrayList = new ArrayList<>();
        propertyArrayList.add(new Property("coordinateX", notificationProperties.getCoordinateX()));
        propertyArrayList.add(new Property("coordinateY", notificationProperties.getCoordinateY()));

        return propertyArrayList;
    }
}

