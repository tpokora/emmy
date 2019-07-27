package org.tpokora.views.weather;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.board.Board;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.tpokora.storms.model.Coordinates;
import org.tpokora.storms.model.Warning;
import org.tpokora.storms.services.FindWarningService;
import org.tpokora.views.common.BaseForm;

import javax.xml.soap.SOAPException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

@Tag("find-warnings-form")
public class FindWarningsForm extends BaseForm {

    private Coordinates coordinates;
    private Set<Warning> warningsSet;
    private ArrayList<WarningElement> warningElementArrayList;

    private FindWarningService findWarningService;

    private FormLayout layoutWithFormItems;
    private TextField errorTextLabel;
    private TextField xCoordinatesTextField;
    private TextField yCoordinatesTextField;

    private HorizontalLayout buttonsLayout;
    private Button findWarningsBtn;
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
        this.findWarningsBtn = new Button("Find warnings");
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
        this.buttonsLayout.add(this.findWarningsBtn, this.resetBtn);
        this.layoutWithFormItems.add(this.buttonsLayout);
        setupFormButtonsActions();
    }

    protected void setupFormButtonsActions() {
        this.findWarningsBtn.addClickListener(e -> {
            try {
                this.coordinates = new Coordinates(Double.valueOf(this.xCoordinatesTextField.getValue()),
                        Double.valueOf(this.yCoordinatesTextField.getValue()));
                this.warningsSet = this.findWarningService.handleResponse(this.findWarningService.findWarning(this.coordinates));
                this.setupResponseForm();
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

    private void setupResponseForm() {
        this.warningElementArrayList = new ArrayList<>();
        for (Warning warning : warningsSet) {
            this.warningElementArrayList.add(new WarningElement(warning));
            this.warningElementArrayList.add(new WarningElement(warning));
            this.warningElementArrayList.add(new WarningElement(warning));
        }
        Board board = new Board();
        this.warningElementArrayList.stream().forEach(warningElement -> {
            board.addRow(warningElement);
        });
        this.layoutWithFormItems.add(board);
    }
}
