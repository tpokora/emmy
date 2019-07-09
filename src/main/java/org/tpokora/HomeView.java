package org.tpokora;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "home")
public class HomeView extends VerticalLayout {

    public HomeView() {
        Button configBtn = new Button("Config");
        configBtn.addClickListener(e -> {
            configBtn.getUI().ifPresent(ui -> ui.navigate("config"));
        });
        add(configBtn);
    }
}
