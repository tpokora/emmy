package org.tpokora.views;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.tpokora.views.main.Footer;
import org.tpokora.views.main.Header;
import org.tpokora.views.main.Main;

@Route(value = HomeView.ROUTE)
public class HomeView extends VerticalLayout {


    public static final String ROUTE = "home";
    public static final String WELCOME_TO_EMMY_APP = "Welcome to Emmy App!";
    public static final String CONFIG_BTN_TEXT = "Config";
    public static final String ADD_USER_BTN_TEXT = "Create User";

    public HomeView() {
//        Button configBtn = new Button(CONFIG_BTN_TEXT);
//        configBtn.addClickListener(e -> {
//            configBtn.getUI().ifPresent(ui -> ui.navigate("config"));
//        });
//        Button addUserBtn = new Button(ADD_USER_BTN_TEXT);
//        addUserBtn.addClickListener(e -> {
//            addUserBtn.getUI().ifPresent(ui -> ui.navigate("signup"));
//        });
//        HorizontalLayout buttonsLayout = new HorizontalLayout();
//        buttonsLayout.add(configBtn, addUserBtn);
//        layout.add(
//                new H1(WELCOME_TO_EMMY_APP),
//                buttonsLayout);
        getStyle().set("border", "1px solid #9E9E9E");
        setDefaultHorizontalComponentAlignment(
                FlexComponent.Alignment.STRETCH);

        createComponents();
    }

    private void createComponents() {
        Div header = new Header();
        expand(header);

        Div main = new Main();
        setFlexGrow(2, main);

        Div footer = new Footer();
        setFlexGrow(0.5, footer);

        add(header, main, footer);
    }
}
