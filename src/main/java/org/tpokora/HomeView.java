package org.tpokora;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "home")
public class HomeView extends VerticalLayout {

    public static final String WELCOME_TO_EMMY_APP = "Welcome to Emmy App!";
    public static final String CONFIG_BTN_TEXT = "Config";

    public HomeView() {
        Button configBtn = new Button(CONFIG_BTN_TEXT);
        configBtn.addClickListener(e -> {
            configBtn.getUI().ifPresent(ui -> ui.navigate("config"));
        });
        add(
                new H1(WELCOME_TO_EMMY_APP),
                configBtn);
    }
}
