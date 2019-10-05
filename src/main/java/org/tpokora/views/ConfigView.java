package org.tpokora.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.tpokora.config.properties.AppProperties;
import org.tpokora.config.properties.FirebaseProperties;
import org.tpokora.config.model.Property;
import org.tpokora.config.properties.NotificationProperties;
import org.tpokora.views.common.RouteStrings;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Tag("config-view")
@Route(value = RouteStrings.CONFIG_ROUTE, layout = MainView.class)
@PageTitle(RouteStrings.CONFIG)
public class ConfigView extends AbstractView {

    private final ArrayList<Property> allProperties;
    private FirebaseProperties firebaseProperties;
    private AppProperties appProperties;
    private NotificationProperties notificationProperties;

    VerticalLayout verticalLayout = new VerticalLayout();
    Grid<Property> grid;

    public ConfigView(FirebaseProperties firebaseProperties, AppProperties appProperties, NotificationProperties notificationProperties) {
        this.firebaseProperties = firebaseProperties;
        this.appProperties = appProperties;
        this.notificationProperties = notificationProperties;
        this.allProperties = getAllProperties();

        this.verticalLayout.add(new H3(RouteStrings.CONFIG));
        this.grid = new Grid<>(Property.class);
        this.grid.setSelectionMode(Grid.SelectionMode.MULTI);
        this.grid.setColumns("property", "value");
        this.grid.setItems(this.allProperties);
        gridDeselectAll();

        TextField filterField = new TextField();
        filterField.setValueChangeMode(ValueChangeMode.EAGER);
        filterField.addValueChangeListener(event -> {
            if (event.getValue().length() > 2) {
                Set<Property> foundProperties = allProperties.stream().filter(
                        property -> property.getProperty().toLowerCase()
                                .startsWith(event.getValue().toLowerCase()))
                        .collect(Collectors.toSet());

                this.grid.asMultiSelect().setValue(foundProperties);
            } else {
                gridDeselectAll();
            }
        });

        this.verticalLayout.add(filterField, this.grid);

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

    private void gridDeselectAll() {
        this.grid.deselectAll();
    }
}

