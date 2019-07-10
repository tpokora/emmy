package org.tpokora.views;

import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = LoginView.ROUTE)
public class LoginView extends VerticalLayout {
    public static final String ROUTE = "login";

    private LoginOverlay login = new LoginOverlay();

    public LoginView() {
        login.setAction("login");
        login.setOpened(true);
        login.setTitle("Vaadin Login");
        login.setDescription("Vaadin simple login");
        getElement().appendChild(login.getElement());
    }
}
