package org.tpokora.views.weather;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.tpokora.storms.model.City;
import org.tpokora.storms.services.FindCityService;
import org.tpokora.views.common.Styler;

import javax.xml.soap.SOAPException;
import java.io.IOException;

@Tag("find-city-form")
public class FindCityForm extends Div {

    private City city;

    private FindCityService findCityService;

    private TextField errorTextLabel;
    private TextField cityName;
    private TextField xCoordinatesTextField;
    private TextField yCoordinatesTextField;
    private Button findCityBtn;
    private Button resetBtn;

    public FindCityForm(City city, FindCityService findCityService) {
        this.city = city;
        this.findCityService = findCityService;
        this.errorTextLabel = new TextField();
        this.cityName = new TextField();
        this.xCoordinatesTextField = new TextField();
        this.yCoordinatesTextField = new TextField();
        this.findCityBtn = new Button("Find city");
        this.resetBtn = new Button("Reset");
        setupForm();
    }

    private void setupForm() {
        Styler.setAutoMargin(this);
        FormLayout layoutWithFormItems = new FormLayout();
        layoutWithFormItems.setWidth("600px");

        this.cityName.setPlaceholder("Kraków");
        this.xCoordinatesTextField.setPlaceholder(String.valueOf(this.city.getCoordinates().getX()));
        this.yCoordinatesTextField.setPlaceholder(String.valueOf(this.city.getCoordinates().getY()));
        layoutWithFormItems.addFormItem(cityName, "City");
        layoutWithFormItems.addFormItem(xCoordinatesTextField, "X");
        layoutWithFormItems.addFormItem(yCoordinatesTextField, "Y");

        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.add(this.findCityBtn, this.resetBtn);

        this.findCityBtn.addClickListener(e -> {
            try {
                City city = this.findCityService.handleResponse(this.findCityService.findCity(this.cityName.getValue()));
                this.xCoordinatesTextField.setValue(String.valueOf(city.getCoordinates().getX()));
                this.yCoordinatesTextField.setValue(String.valueOf(city.getCoordinates().getY()));
            } catch (SOAPException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        this.resetBtn.addClickListener(e -> {
            this.city = new City();
            this.xCoordinatesTextField.setValue(String.valueOf(this.city.getCoordinates().getX()));
            this.yCoordinatesTextField.setValue(String.valueOf(this.city.getCoordinates().getY()));
            this.cityName.setValue("");
        });

        layoutWithFormItems.add(buttonsLayout);
        add(layoutWithFormItems);
    }

    private void handleServiceError(City city) {
    }
}
