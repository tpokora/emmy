package org.tpokora.views.weather;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.tpokora.storms.model.Coordinates;
import org.tpokora.storms.model.Warning;
import org.tpokora.storms.services.FindWarningService;
import org.tpokora.views.common.BaseForm;

import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.util.Set;

@Tag("find-warnings-form")
public class FindWarningsForm extends BaseForm {

    private Coordinates coordinates;
    private Set<Warning> warningsSet;

    private FindWarningService findWarningService;

    private FormLayout layoutWithFormItems;
    private TextField errorTextLabel;
    private TextField xCoordinatesTextField;
    private TextField yCoordinatesTextField;

    private HorizontalLayout buttonsLayout;
    private Button findStormBtn;
    private Button resetBtn;

    public FindWarningsForm(Coordinates coordinates, FindWarningService findWarningService) {
        this.coordinates = coordinates;
        this.findWarningService = findWarningService;

        initializeElements();
        setupForm();
        add(this.layoutWithFormItems);
    }

    protected void initializeElements() {
        this.layoutWithFormItems = new FormLayout();
        this.errorTextLabel = new TextField();
        this.xCoordinatesTextField = new TextField();
        this.yCoordinatesTextField = new TextField();

        this.buttonsLayout = new HorizontalLayout();
        this.findStormBtn = new Button("Find storm");
        this.resetBtn = new Button("Reset");
    }

    protected void setupForm() {
        setupWarningsForm();
    }

    private void setupWarningsForm() {
        this.xCoordinatesTextField.setValue(String.valueOf(this.coordinates.getX()));
        this.yCoordinatesTextField.setValue(String.valueOf(this.coordinates.getY()));
        this.layoutWithFormItems.addFormItem(this.xCoordinatesTextField, "X");
        this.layoutWithFormItems.addFormItem(this.yCoordinatesTextField, "Y");
        this.buttonsLayout.add(this.findStormBtn, this.resetBtn);
        this.layoutWithFormItems.add(this.buttonsLayout);
        setupFormButtonsActions();
    }

    protected void setupFormButtonsActions() {
        this.findStormBtn.addClickListener(e -> {
            try {
                this.coordinates = new Coordinates(Double.valueOf(this.xCoordinatesTextField.getValue()),
                        Double.valueOf(this.yCoordinatesTextField.getValue()));
                this.warningsSet = this.findWarningService.handleResponse(this.findWarningService.findWarning(this.coordinates));
            } catch (SOAPException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        this.resetBtn.addClickListener(e -> {
            this.coordinates = new Coordinates();
            this.xCoordinatesTextField.setValue(String.valueOf(this.coordinates.getX()));
            this.yCoordinatesTextField.setValue(String.valueOf(this.coordinates.getY()));
        });
    }
}
