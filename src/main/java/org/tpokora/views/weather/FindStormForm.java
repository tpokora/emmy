package org.tpokora.views.weather;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.tpokora.storms.model.Coordinates;
import org.tpokora.storms.model.StormRequest;
import org.tpokora.storms.model.StormResponse;
import org.tpokora.storms.services.FindStormService;

import javax.xml.soap.SOAPException;
import java.io.IOException;

@Tag("find-storm-form")
public class FindStormForm extends Div {

    private Coordinates coordinates;
    private StormResponse stormResponse;

    private FindStormService findStormService;

    private FormLayout layoutWithFormItems;
    private TextField errorTextLabel;
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

    public FindStormForm(Coordinates coordinates, FindStormService findStormService) {
        this.stormResponse = new StormResponse();
        this.coordinates = coordinates;
        this.findStormService = findStormService;

        this.layoutWithFormItems = new FormLayout();
        this.errorTextLabel = new TextField();
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
        setupForm();
    }

    private void setupForm() {
        setupStormForm();
        setupResponseLayout();

        this.layoutWithFormItems.add(this.buttonsLayout);
        this.layoutWithFormItems.add(this.stormResponseLayout);

        add(this.layoutWithFormItems);
    }

    private void setupStormForm() {
        this.amountTextField.setLabel("Amount");
        this.distanceTextField.setLabel("Distance");
        this.directionTextField.setLabel("Direction");
        this.timeTextField.setLabel("Time");

        this.xCoordinatesTextField.setValue(String.valueOf(this.coordinates.getX()));
        this.yCoordinatesTextField.setValue(String.valueOf(this.coordinates.getY()));
        this.layoutWithFormItems.addFormItem(xCoordinatesTextField, "X");
        this.layoutWithFormItems.addFormItem(yCoordinatesTextField, "Y");
        this.layoutWithFormItems.addFormItem(radiusTextField, "Radius");

        setupStormFormButtonsActions();
    }

    private void setupStormFormButtonsActions() {
        this.findStormBtn.addClickListener(e -> {
            try {
                StormRequest stormRequest = new StormRequest();
                this.coordinates = new Coordinates(Double.valueOf(this.xCoordinatesTextField.getValue()),
                        Double.valueOf(this.yCoordinatesTextField.getValue()));
                stormRequest.setCoordinates(this.coordinates);
                stormRequest.setDistance(Float.parseFloat(this.radiusTextField.getValue()));
                this.stormResponse = this.findStormService.handleResponse(this.findStormService.checkStorm(stormRequest));
                this.amountTextField.setValue(String.valueOf(this.stormResponse.getAmount()));
                this.distanceTextField.setValue(String.valueOf(this.stormResponse.getDistance()));
                this.directionTextField.setValue(this.stormResponse.getDirection());
                this.timeTextField.setValue(String.valueOf(this.stormResponse.getTime()));
            } catch (SOAPException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        this.resetBtn.addClickListener(e -> {
            this.coordinates = new Coordinates();
            this.stormResponse = new StormResponse();
            this.amountTextField.setValue(String.valueOf(this.stormResponse.getAmount()));
            this.distanceTextField.setValue(String.valueOf(this.stormResponse.getDistance()));
            this.directionTextField.setValue(this.stormResponse.getDirection());
            this.timeTextField.setValue(String.valueOf(this.stormResponse.getTime()));
            this.xCoordinatesTextField.setValue(String.valueOf(this.coordinates.getX()));
            this.yCoordinatesTextField.setValue(String.valueOf(this.coordinates.getY()));
        });

        this.buttonsLayout.add(this.findStormBtn, this.resetBtn);
    }

    private void setupResponseLayout() {
        this.amountTextField.setValue(String.valueOf(this.stormResponse.getAmount()));
        this.distanceTextField.setValue(String.valueOf(this.stormResponse.getDistance()));
        this.directionTextField.setValue(this.stormResponse.getDirection());
        this.timeTextField.setValue(String.valueOf(this.stormResponse.getTime()));
        this.stormResponseLayout.add(this.amountTextField, this.distanceTextField, this.directionTextField, this.timeTextField);
    }
}
