package org.tpokora.views.users;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.NativeButton;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.tpokora.views.MainView;
import org.tpokora.views.common.RouteStrings;

@Tag("signup-view")
@Route(value = RouteStrings.SINGUP_ROUTE, layout = MainView.class)
@PageTitle(RouteStrings.SINGUP)
public class RegistrationView extends VerticalLayout {

    //TODO: finish registration form
    public RegistrationView() {
        FormLayout layout = new FormLayout();
        TextField loginField = new TextField();
        loginField.setValueChangeMode(ValueChangeMode.EAGER);
        TextField emailField = new TextField();
        emailField.setValueChangeMode(ValueChangeMode.EAGER);
        TextField passwordField = new TextField();
        passwordField.setValueChangeMode(ValueChangeMode.EAGER);

        NativeButton save = new NativeButton("Save");
        NativeButton reset = new NativeButton("Reset");

        layout.addFormItem(loginField, "Login");
        layout.addFormItem(emailField, "Email");
        layout.addFormItem(passwordField, "Password");

        HorizontalLayout actions = new HorizontalLayout();
        actions.add(save, reset);

        layout.add(actions);

        getElement().appendChild(layout.getElement());
    }


}
