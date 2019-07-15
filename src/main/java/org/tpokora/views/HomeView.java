package org.tpokora.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = HomeView.ROUTE)
public class HomeView extends VerticalLayout {

    public static final String ROUTE = "home";
    public static final String WELCOME_TO_EMMY_APP = "Welcome to Emmy App!";
    public static final String CONFIG_BTN_TEXT = "Config";
    public static final String ADD_USER_BTN_TEXT = "Create User";

    public HomeView() {
        Button configBtn = new Button(CONFIG_BTN_TEXT);
        configBtn.addClickListener(e -> {
            configBtn.getUI().ifPresent(ui -> ui.navigate("config"));
        });
        Button addUserBtn = new Button(ADD_USER_BTN_TEXT);
        addUserBtn.addClickListener(e -> {
            addUserBtn.getUI().ifPresent(ui -> ui.navigate("signup"));
        });
        HorizontalLayout buttonsLayout = new HorizontalLayout();
        buttonsLayout.add(configBtn, addUserBtn);
        add(
                new H1(WELCOME_TO_EMMY_APP),
                buttonsLayout);
    }
}
