package org.tpokora.views;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.validator.StringLengthValidator;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.tpokora.config.model.Property;
import org.tpokora.config.properties.AppProperties;
import org.tpokora.config.properties.FirebaseProperties;
import org.tpokora.config.properties.NotificationProperties;
import org.tpokora.views.common.RouteStrings;

import java.util.*;
import java.util.stream.Collectors;

@Tag("config-view")
@Route(value = RouteStrings.CONFIG_ROUTE, layout = MainView.class)
@PageTitle(RouteStrings.CONFIG)
public class ConfigView extends AbstractView {

    private final ArrayList<Property> allProperties;
    private FirebaseProperties firebaseProperties;
    private AppProperties appProperties;
    private NotificationProperties notificationProperties;

    VerticalLayout gridLayout = new VerticalLayout();
    FormLayout gridLayoutHeader = new FormLayout();
    Grid<Property> grid;

    private TextField filterField;
    private Div validationStatus;

    public ConfigView(FirebaseProperties firebaseProperties, AppProperties appProperties, NotificationProperties notificationProperties) {
        this.firebaseProperties = firebaseProperties;
        this.appProperties = appProperties;
        this.notificationProperties = notificationProperties;
        this.allProperties = getAllProperties();

        gridLayoutHeaderSetup();
        gridSetup();
        setupContentDefaultStyles();
        addToContent(this.gridLayout);
    }

    private void gridLayoutHeaderSetup() {
        filterFieldSetup();
        validationStatusSetup();
        this.gridLayoutHeader.addFormItem(this.filterField, "Filter");
        this.gridLayoutHeader.add(this.validationStatus);
    }

    private void validationStatusSetup() {
        this.validationStatus = new Div();
        this.validationStatus.setId("validation");
        this.validationStatus.getStyle().set("color", "#d61010");
        this.validationStatus.getStyle().set("font-weight", "500");
    }

    private void gridSetup() {
        this.gridLayout.add(new H3(RouteStrings.CONFIG));
        this.grid = new Grid<>();
        this.grid.setSelectionMode(Grid.SelectionMode.MULTI);
        this.grid.setItems(this.allProperties);

        Grid.Column<Property> propertyColumn = this.grid.addColumn(Property::getProperty).setHeader("Property");
        Grid.Column<Property> valueColumn = this.grid.addColumn(Property::getValue).setHeader("Value");
        gridDeselectAll();
        Binder<Property> binder = new Binder<>(Property.class);
        Editor<Property> editor = this.grid.getEditor();
        editor.setBinder(binder);
        editor.setBuffered(true);

        TextField valueField = new TextField();
        binder.forField(valueField)
                .withValidator(new StringLengthValidator("Value can't be empty", 5, 100))
                .withStatusLabel(this.validationStatus).bind("value");
        valueColumn.setEditorComponent(valueField);

        editorButtonsSetup(editor, valueField);

        this.gridLayout.add(this.gridLayoutHeader, this.grid);
    }

    private void editorButtonsSetup(Editor<Property> editor, TextField valueField) {
        Collection<Button> editButtons = Collections.newSetFromMap(new WeakHashMap<>());
        Grid.Column<Property> editorColumn = this.grid.addComponentColumn(property -> {
            Button edit = new Button("Edit");
            edit.addClassName("edit");
            edit.addClickListener(e -> {
                editor.editItem(property);
                valueField.focus();
            });
            edit.setEnabled(!editor.isOpen());
            editButtons.add(edit);
            return edit;
        });

        editor.addOpenListener(e -> editButtons.stream()
                .forEach(button -> button.setEnabled(!editor.isOpen())));
        editor.addCloseListener(e -> editButtons.stream()
                .forEach(button -> button.setEnabled(!editor.isOpen())));

        Button save = new Button("Save", e -> editor.save());
        save.addClassName("save");
        Button cancel = new Button("Cancel", e -> editor.cancel());
        cancel.addClassName("cancel");

        this.grid.getElement().addEventListener("keyup", event -> editor.cancel())
                .setFilter("event.key === 'Escape' || event.key === 'Esc'");

        Div buttons = new Div(save, cancel);
        editorColumn.setEditorComponent(buttons);
    }

    private void filterFieldSetup() {
        this.filterField = new TextField();
        this.filterField.setValueChangeMode(ValueChangeMode.EAGER);
        this.filterField.addValueChangeListener(event -> {
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

