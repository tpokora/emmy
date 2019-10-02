package org.tpokora.views.weather;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.tpokora.storms.model.City;
import org.tpokora.storms.model.Warning;
import org.tpokora.storms.services.FindWarningService;
import org.tpokora.views.common.BaseForm;

import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

@Tag("find-warnings-form")
public class FindWarningsForm extends BaseForm {

    private Set<Warning> warningsSet;
    private ArrayList<WarningElement> warningElementArrayList;

    private FindWarningService findWarningService;
    private WeatherService weatherService;

    private FormLayout layoutWithFormItems;
    private TextField xCoordinatesTextField;
    private TextField yCoordinatesTextField;

    private VerticalLayout warningsLayout;
    private HorizontalLayout buttonsLayout;
    private Button findWarningsBtn;
    private Button resetBtn;

    public FindWarningsForm(WeatherService weatherService, FindWarningService findWarningService) {
        this.weatherService = weatherService;
        this.findWarningService = findWarningService;

        initializeElements();
        setupForm();
        add(this.layoutWithFormItems);
        add(this.warningsLayout);
    }

    protected void initializeElements() {
        this.layoutWithFormItems = new FormLayout();
        this.layoutWithFormItems.setWidth("auto");
        this.xCoordinatesTextField = new TextField();
        this.yCoordinatesTextField = new TextField();

        this.warningsLayout = new VerticalLayout();
        this.warningsLayout.getStyle().set("margin-top", "40px");
        this.buttonsLayout = new HorizontalLayout();
        this.findWarningsBtn = new Button("Find warnings");
        this.resetBtn = new Button("Reset");
    }

    protected void setupForm() {
        setupWarningsForm();
    }

    private void setupWarningsForm() {
        this.xCoordinatesTextField.setValue(String.valueOf(this.weatherService.getCity().getCoordinates().getX()));
        this.yCoordinatesTextField.setValue(String.valueOf(this.weatherService.getCity().getCoordinates().getY()));
        this.layoutWithFormItems.addFormItem(this.xCoordinatesTextField, "X");
        this.layoutWithFormItems.addFormItem(this.yCoordinatesTextField, "Y");
        this.buttonsLayout.add(this.findWarningsBtn, this.resetBtn);
        this.layoutWithFormItems.add(this.buttonsLayout);
        setupFormButtonsActions();
    }

    protected void setupFormButtonsActions() {
        this.findWarningsBtn.addClickListener(e -> {
            try {
                this.warningsSet = this.findWarningService.handleResponse(this.findWarningService.findWarning(this.weatherService.getCity().getCoordinates()));
                this.setupResponseForm();
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
        });
    }

    private void setupResponseForm() {
        this.warningElementArrayList = new ArrayList<>();
        for (Warning warning : warningsSet) {
            this.warningElementArrayList.add(new WarningElement(warning));
        }

        createWarningsBoard();
    }

    private void createWarningsBoard() {
//        this.warningElementArrayList.add(new WarningElement(new Warning("TEST", 1, LocalDateTime.now(), LocalDateTime.now().plusDays(3))));
//        this.warningElementArrayList.add(new WarningElement(new Warning("TEST1", 1, LocalDateTime.now(), LocalDateTime.now().plusDays(3))));
//        this.warningElementArrayList.add(new WarningElement(new Warning("TEST2", 1, LocalDateTime.now(), LocalDateTime.now().plusDays(3))));
        for (WarningElement element : this.warningElementArrayList) {
            this.warningsLayout.add(element);
        }
    }

    public void refreshInputs() {
        this.xCoordinatesTextField.setValue(String.valueOf(this.weatherService.getCity().getCoordinates().getX()));
        this.yCoordinatesTextField.setValue(String.valueOf(this.weatherService.getCity().getCoordinates().getY()));
    }
}
