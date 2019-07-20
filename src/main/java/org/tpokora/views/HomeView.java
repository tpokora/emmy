package org.tpokora.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.AppLayoutMenu;
import com.vaadin.flow.component.applayout.AppLayoutMenuItem;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.Route;

@Route(value = HomeView.ROUTE)
public class HomeView extends AppLayout {


    public static final String ROUTE = "home";
    public static final String WELCOME_TO_EMMY_APP = "Welcome to Emmy App!";
    public static final String CONFIG_BTN_TEXT = "Config";
    public static final String ADD_USER_BTN_TEXT = "Create User";

    public HomeView() {
        AppLayoutMenu menu = createMenu();

        setBranding(new H3(WELCOME_TO_EMMY_APP));

        menu.addMenuItems(new AppLayoutMenuItem(ADD_USER_BTN_TEXT, "signup"),
                new AppLayoutMenuItem(CONFIG_BTN_TEXT, "config"));

        Component content = new Span(new H3("Page title"),
                new Span("Page content"));
        setContent(content);
    }
}
