package org.tpokora.views;

import com.vaadin.flow.component.login.LoginOverlay;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.tpokora.views.common.RouteStrings;

@Route(value = RouteStrings.LOGIN_ROUTE)
@PageTitle(RouteStrings.LOGIN)
public class LoginView extends VerticalLayout {

    private LoginOverlay login = new LoginOverlay();

    public LoginView() {
        login.setAction("login");
        login.setOpened(true);
        login.setTitle("Vaadin Login");
        login.setDescription("Vaadin simple login");
        getElement().appendChild(login.getElement());
    }
}
