package org.tpokora.views.common;

import com.vaadin.flow.component.html.Div;

public abstract class BaseForm extends Div {

    protected abstract void initializeElements();
    protected abstract void setupForm();
    protected abstract void setupFormButtonsActions();
}
