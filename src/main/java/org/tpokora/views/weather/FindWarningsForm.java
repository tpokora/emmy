package org.tpokora.views.weather;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.tpokora.storms.model.City;
import org.tpokora.storms.model.Period;
import org.tpokora.storms.model.Warning;
import org.tpokora.storms.model.WarningStrings;
import org.tpokora.storms.services.FindWarningService;
import org.tpokora.views.common.BaseForm;
import org.tpokora.views.common.Styler;

import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

@Tag("find-warnings-form")
public class FindWarningsForm extends BaseForm {

    private Set<Warning> warningsSet;
    private ArrayList<WarningElement> warningElementArrayList;

    private FindWarningService findWarningService;
    private WeatherService weatherService;

    private FormLayout layoutWithFormItems;
    private TextField errorTextLabel;
    private TextField xCoordinatesTextField;
    private TextField yCoordinatesTextField;

    private HorizontalLayout buttonsLayout;
    private Button findWarningsBtn;
    private Button resetBtn;

    public FindWarningsForm(WeatherService weatherService, FindWarningService findWarningService) {
        this.weatherService = weatherService;
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
            Notification.show(this.weatherService.getCity().toString());
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
        Board board = new Board();
        for (int i = 0; i < this.warningElementArrayList.size() - 1; i = i + 2) {
            WarningElement warningElementFirst = this.warningElementArrayList.get(i);
            Optional<WarningElement> warningElementSecond = Optional.ofNullable(this.warningElementArrayList.get(i + 1));
            board.addRow(warningElementFirst, warningElementSecond.get());
        }

        if (this.warningElementArrayList.size() % 2 != 0) {
            board.addRow(this.warningElementArrayList.get(this.warningElementArrayList.size() - 1));
        }
        Div boardWrapper = new Div();
        Styler.setAutoMargin(boardWrapper);
        boardWrapper.setWidth("70%");
        boardWrapper.add(board);
        this.layoutWithFormItems.add(boardWrapper);
    }

    public void refreshInputs() {
        this.xCoordinatesTextField.setValue(String.valueOf(this.weatherService.getCity().getCoordinates().getX()));
        this.yCoordinatesTextField.setValue(String.valueOf(this.weatherService.getCity().getCoordinates().getY()));
    }
}
