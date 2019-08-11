package org.tpokora.views.common;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.TextField;

public abstract class BaseForm extends Div {

    protected TextField errorTextLabel;

    public BaseForm() {
        this.errorTextLabel = new TextField();
    }

    protected abstract void initializeElements();
    protected abstract void setupForm();
    protected abstract void setupFormButtonsActions();
}
