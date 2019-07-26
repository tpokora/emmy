package org.tpokora.views.weather;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;
import org.tpokora.storms.model.City;
import org.tpokora.storms.services.FindCityService;
import org.tpokora.views.common.Styler;

import javax.xml.soap.SOAPException;
import java.io.IOException;

@Tag("find-city-form")
public class FindCityForm extends Div {

    private FindCityService findCityService;

    private TextField errorTextLabel;
    private TextField cityName;
    private TextField xCoordinatesTextField;
    private TextField yCoordinatesTextField;
    private Button findCityBtn;

    public FindCityForm(City city, FindCityService findCityService) {
        this.findCityService = findCityService;
        this.errorTextLabel = new TextField();
        this.cityName = new TextField();
        this.xCoordinatesTextField = new TextField();
        this.yCoordinatesTextField = new TextField();
        this.findCityBtn = new Button("Find city");
        setupForm();
    }

    private void setupForm() {
        Styler.setAutoMargin(this);
        FormLayout layoutWithFormItems = new FormLayout();
        layoutWithFormItems.setWidth("600px");

        this.cityName.setPlaceholder("Kraków");
        this.xCoordinatesTextField.setPlaceholder("0.0");
        this.yCoordinatesTextField.setPlaceholder("0.0");
        layoutWithFormItems.addFormItem(cityName, "City");
        layoutWithFormItems.addFormItem(xCoordinatesTextField, "X");
        layoutWithFormItems.addFormItem(yCoordinatesTextField, "Y");

        this.findCityBtn.addClickListener(e -> {
            try {
                City city = this.findCityService.handleResponse(this.findCityService.findCity(this.cityName.getValue()));
                this.xCoordinatesTextField.setValue(city.getCoordinates().getX());
                this.yCoordinatesTextField.setValue(city.getCoordinates().getY());
            } catch (SOAPException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });


        layoutWithFormItems.add(this.findCityBtn);
        this.findCityBtn.getStyle().set("width", "100px");
        add(layoutWithFormItems);
    }

    private void handleServiceError(City city) {
    }
}
