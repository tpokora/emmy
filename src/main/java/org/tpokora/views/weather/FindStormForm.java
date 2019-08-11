package org.tpokora.views.weather;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.tpokora.storms.model.City;
import org.tpokora.storms.model.StormRequest;
import org.tpokora.storms.model.StormResponse;
import org.tpokora.storms.services.FindStormService;
import org.tpokora.views.common.BaseForm;

import javax.xml.soap.SOAPException;
import java.io.IOException;

@Tag("find-storm-form")
public class FindStormForm extends BaseForm {

    private StormResponse stormResponse;

    private WeatherService weatherService;
    private FindStormService findStormService;

    private FormLayout layoutWithFormItems;
    private TextField xCoordinatesTextField;
    private TextField yCoordinatesTextField;
    private TextField radiusTextField;

    private HorizontalLayout buttonsLayout;
    private Button findStormBtn;
    private Button resetBtn;

    private HorizontalLayout stormResponseLayout;
    private TextField amountTextField;
    private TextField distanceTextField;
    private TextField directionTextField;
    private TextField timeTextField;

    public FindStormForm(WeatherService weatherService, FindStormService findStormService) {
        this.stormResponse = new StormResponse();
        this.weatherService = weatherService;
        this.findStormService = findStormService;

        initializeElements();
        setupForm();
        add(this.layoutWithFormItems);
    }

    protected void initializeElements() {
        this.layoutWithFormItems = new FormLayout();
        this.xCoordinatesTextField = new TextField();
        this.yCoordinatesTextField = new TextField();
        this.radiusTextField = new TextField();

        this.stormResponseLayout = new HorizontalLayout();
        this.amountTextField = new TextField();
        this.distanceTextField = new TextField();
        this.directionTextField = new TextField();
        this.timeTextField = new TextField();

        this.buttonsLayout = new HorizontalLayout();
        this.findStormBtn = new Button("Find storm");
        this.resetBtn = new Button("Reset");
    }

    protected void setupForm() {
        setupStormForm();
        setupResponseLayout();
    }

    private void setupStormForm() {
        this.xCoordinatesTextField.setValue(String.valueOf(this.weatherService.getCity().getCoordinates().getX()));
        this.yCoordinatesTextField.setValue(String.valueOf(this.weatherService.getCity().getCoordinates().getY()));
        this.layoutWithFormItems.addFormItem(xCoordinatesTextField, "X");
        this.layoutWithFormItems.addFormItem(yCoordinatesTextField, "Y");
        this.layoutWithFormItems.addFormItem(radiusTextField, "Radius");
        this.buttonsLayout.add(this.findStormBtn, this.resetBtn);
        this.layoutWithFormItems.add(this.buttonsLayout);
        setupFormButtonsActions();
    }

    protected void setupFormButtonsActions() {
        this.findStormBtn.addClickListener(e -> {
            try {
                StormRequest stormRequest = new StormRequest();
                stormRequest.setCoordinates(this.weatherService.getCity().getCoordinates());
                stormRequest.setDistance(Float.parseFloat(this.radiusTextField.getValue()));
                this.stormResponse = this.findStormService.handleResponse(this.findStormService.checkStorm(stormRequest));
                this.amountTextField.setValue(String.valueOf(this.stormResponse.getAmount()));
                this.distanceTextField.setValue(String.valueOf(this.stormResponse.getDistance()));
                this.directionTextField.setValue(this.stormResponse.getDirection());
                this.timeTextField.setValue(String.valueOf(this.stormResponse.getTime()));
                Notification.show(this.weatherService.getCity().toString());
            } catch (SOAPException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        this.resetBtn.addClickListener(e -> {
            this.stormResponse = new StormResponse();
            this.weatherService.setCity(new City());
            this.amountTextField.setValue(String.valueOf(this.stormResponse.getAmount()));
            this.distanceTextField.setValue(String.valueOf(this.stormResponse.getDistance()));
            this.directionTextField.setValue(this.stormResponse.getDirection());
            this.timeTextField.setValue(String.valueOf(this.stormResponse.getTime()));
            this.xCoordinatesTextField.setValue(String.valueOf(this.weatherService.getCity().getCoordinates().getX()));
            this.yCoordinatesTextField.setValue(String.valueOf(this.weatherService.getCity().getCoordinates().getY()));
            Notification.show(this.weatherService.getCity().toString());
        });
    }

    private void setupResponseLayout() {
        this.amountTextField.setLabel("Amount");
        this.distanceTextField.setLabel("Distance");
        this.directionTextField.setLabel("Direction");
        this.timeTextField.setLabel("Time");
        this.amountTextField.setValue(String.valueOf(this.stormResponse.getAmount()));
        this.distanceTextField.setValue(String.valueOf(this.stormResponse.getDistance()));
        this.directionTextField.setValue(this.stormResponse.getDirection());
        this.timeTextField.setValue(String.valueOf(this.stormResponse.getTime()));
        this.stormResponseLayout.add(this.amountTextField, this.distanceTextField, this.directionTextField, this.timeTextField);
        this.layoutWithFormItems.add(this.stormResponseLayout);
    }

    public void refreshInputs() {
        this.xCoordinatesTextField.setValue(String.valueOf(this.weatherService.getCity().getCoordinates().getX()));
        this.yCoordinatesTextField.setValue(String.valueOf(this.weatherService.getCity().getCoordinates().getY()));
    }
}
