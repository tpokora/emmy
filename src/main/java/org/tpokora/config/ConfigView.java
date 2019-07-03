package org.tpokora.config;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

import java.util.ArrayList;

@Route(value = "config")
public class ConfigView extends VerticalLayout {

    private FirebaseProperties firebaseProperties;
    Grid<Property> grid;

    public ConfigView(FirebaseProperties firebaseProperties) {
        this.firebaseProperties = firebaseProperties;
        add(new Span("Configuration"));

        this.grid = new Grid<>(Property.class);
        grid.setColumns("property", "value");
        grid.setItems(getFirebaseProperties());

        add(grid);
    }

    private ArrayList<Property> getFirebaseProperties() {
        ArrayList<Property> propertyArrayList = new ArrayList<>();
        propertyArrayList.add(new Property("serverKey", firebaseProperties.getServerKey()));
        propertyArrayList.add(new Property("clientToken", firebaseProperties.getClientToken()));

        return propertyArrayList;

    }
}

