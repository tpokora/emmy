package org.tpokora.views.weather;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.tpokora.storms.model.City;
import org.tpokora.storms.services.FindCityService;
import org.tpokora.views.common.BaseForm;
import org.tpokora.views.common.Styler;

import javax.xml.soap.SOAPException;
import java.io.IOException;

@Tag("find-city-form")
public class FindCityForm extends BaseForm {

    private WeatherService weatherService;
    private FindCityService findCityService;

    private FormLayout layoutWithFormItems;
    private TextField cityName;
    private TextField xCoordinatesTextField;
    private TextField yCoordinatesTextField;
    private HorizontalLayout buttonsLayout;
    private Button findCityBtn;
    private Button resetBtn;

    public FindCityForm(WeatherService weatherService, FindCityService findCityService) {
        this.weatherService = weatherService;
        this.findCityService = findCityService;
        this.layoutWithFormItems = new FormLayout();
        initializeElements();
        setupForm();
        add(this.layoutWithFormItems);
    }

    protected void initializeElements() {
        this.cityName = new TextField();
        this.xCoordinatesTextField = new TextField();
        this.xCoordinatesTextField.setEnabled(false);
        this.yCoordinatesTextField = new TextField();
        this.yCoordinatesTextField.setEnabled(false);
        this.buttonsLayout = new HorizontalLayout();
        this.findCityBtn = new Button("Find city");
        this.resetBtn = new Button("Reset");
    }

    protected void setupForm() {
        setupLayouts();
        setupFormButtonsActions();
    }

    protected void setupFormButtonsActions() {
        this.findCityBtn.addClickListener(e -> {
            try {
                City city = this.findCityService.handleResponse(this.findCityService.findCity(this.cityName.getValue()));
                city.setName(this.cityName.getValue());
                this.weatherService.setCity(city);
                this.xCoordinatesTextField.setValue(String.valueOf(this.weatherService.getCity().getCoordinates().getX()));
                this.yCoordinatesTextField.setValue(String.valueOf(this.weatherService.getCity().getCoordinates().getY()));
                Notification.show(this.weatherService.getCity().toString());
            } catch (SOAPException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        this.resetBtn.addClickListener(e -> {
            this.weatherService.setCity(new City());
            this.xCoordinatesTextField.setValue(String.valueOf(this.weatherService.getCity().getCoordinates().getX()));
            this.yCoordinatesTextField.setValue(String.valueOf(this.weatherService.getCity().getCoordinates().getY()));
            this.cityName.setValue(this.weatherService.getCity().getName());
            Notification.show(this.weatherService.getCity().toString());
        });
    }

    private void setupLayouts() {
        Styler.setAutoMargin(this);
        this.layoutWithFormItems.setWidth("600px");
        this.cityName.setPlaceholder("Kraków");
        this.xCoordinatesTextField.setPlaceholder(String.valueOf(this.weatherService.getCity().getCoordinates().getX()));
        this.yCoordinatesTextField.setPlaceholder(String.valueOf(this.weatherService.getCity().getCoordinates().getY()));
        this.layoutWithFormItems.addFormItem(this.cityName, "City");
        this.layoutWithFormItems.addFormItem(this.xCoordinatesTextField, "X");
        this.layoutWithFormItems.addFormItem(this.yCoordinatesTextField, "Y");

        this.buttonsLayout.add(this.findCityBtn, this.resetBtn);
        this.layoutWithFormItems.add(this.buttonsLayout);
    }

    private void handleServiceError(City city) {
    }
}
